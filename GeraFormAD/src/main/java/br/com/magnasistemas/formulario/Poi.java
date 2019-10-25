package br.com.magnasistemas.formulario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nonnull;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.magnasistemas.GeraFormAD.controller.Compactador;
import br.com.magnasistemas.model.Funcionario;


public class Poi {

	private static final String TAMPLATE = "C:\\dev\\workspaces\\eclipse workspaces\\GeraFormAD\\src\\main\\webapp\\resources\\FORMULÁRIO PADRÃO 2018.xlsx";

	public void gerarPoi() throws IOException {
		Logger logger = Logger.getLogger("br.com.magnasistemas.formulario.Poi");
		@Nonnull
		BufferedReader br = null;
		FileInputStream file = null;
		XSSFWorkbook wb = null;
		try {
			String urlBase = "http://portal.magnasistemas.com.br/magna/pessoal.nsf/LookUserID?<inserir>OpenView&Start=";
			String loginurl = "http://portal.magnasistemas.com.br/names.nsf?Login";
			
			ArrayList<String> listaUrl = new ArrayList<>();
			int contagem = 01;
			
			while(contagem <= 539) {
				String paginacao = Integer.toString(contagem);
				String novaUrl = urlBase.concat(paginacao);
				contagem +=29;
				String inserir = novaUrl.replace("<inserir>", "readviewentries&");
				String add = inserir.concat("&outputformat=json");
				listaUrl.add(add);
			}
			
			NavegaSite navegador = new NavegaSite();
			
			boolean ok = navegador.login(loginurl, "amenghini", "Gernavi15!");
			
			for (String url : listaUrl) {
				if (ok) {
	                br = navegador.openPage(url);
	            }	
			
			// Cria lista de funcionarios
			CriarJson criaJson = new CriarJson();
			
			List<Funcionario> listaFuncionarios = criaJson.consomeJson(br);

//			 Codigo do POI
			file = new FileInputStream(new File(Poi.TAMPLATE));
			wb = new XSSFWorkbook(file);
			XSSFSheet avaliacao = wb.getSheetAt(0);
			
			List<String> listaString = new ArrayList<String>();
			
			for (Funcionario funcionario : listaFuncionarios) {
				Row rowColaborador = avaliacao.getRow(1);
				Cell colaborador = rowColaborador.getCell(2);
				colaborador.setCellValue(funcionario.getNome());

				Row rowCargo = avaliacao.getRow(2);
				Cell cargo = rowCargo.getCell(2);
				cargo.setCellValue(funcionario.getCargo());

				Row rowAvaliador = avaliacao.getRow(3);
				Cell avaliador = rowAvaliador.getCell(2);
				avaliador.setCellValue(funcionario.getSuperior());

				String arquivo = "c:\\zipado.zip\\" + funcionario.getApelido().replace(" ", "_").toLowerCase()
						+ ".xlsx";
				
				listaString.add(arquivo);
				System.out.println(arquivo);
				
				FileOutputStream out = new FileOutputStream(new File(arquivo));
				
				wb.write(out);
				out.close();
				
			}
			
			Compactador.compactarParaZip("C:\\zipado.zip", listaString);
			}
			logger.info("Planilhas geradas com sucesso!");
			System.out.println("Planilhas geradas com sucesso!");
		} catch (FileNotFoundException e) {
			logger.warning("Arquivo não encontrado!");
		} catch (IOException e) {
			logger.warning("Não foi possivel manipular este arquivo!");
		}finally {
			wb.close();
			file.close();
			br.close();
		}
}
}
