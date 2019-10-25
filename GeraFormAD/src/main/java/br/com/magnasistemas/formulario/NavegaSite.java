package br.com.magnasistemas.formulario;

	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

	public class NavegaSite {
	 
	    private HttpClient client = HttpClientBuilder.create().build();
	 
	    public boolean login(final String url, final String user, final String 
	    password) throws IOException {
	 
	        final HttpPost post = new HttpPost(url);
	        boolean result = false;
	         
	        final List<NameValuePair> nameValuePairs =
	                new ArrayList<>();
	        nameValuePairs.add(new BasicNameValuePair("Username", user));
	        nameValuePairs.add(new BasicNameValuePair("Password", password));
	         
	        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8));        
	            
	        post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:18.0) Gecko/20100101 Firefox/18.0");
	         
	        HttpResponse response = client.execute(post);
	        
	        Logger logger = Logger.getLogger("br.com.magnasistemas.formulario.NavegaSite");
	        
	        EntityUtils.consume(response.getEntity());
	        if (checkSuccess(response)) {
	        	logger.info("Conexao Estabelecida!");
	            result = true;
	        } else {
	            logger.info("Login não-efetuado!");
	        }
	 
	        return result;
	    }
	    public BufferedReader openPage(final String url) throws IOException {
	        final HttpGet get = new HttpGet(url);
	        final HttpResponse response = client.execute(get);
	        return criaBufferedReader(response);
	    }
	 
	    public boolean checkSuccess(final HttpResponse response) throws IOException {
	        final BufferedReader rd = new BufferedReader(new InputStreamReader(
	                response.getEntity().getContent()));
	        String line;
	        boolean found = false;
	        while ((line = rd.readLine()) != null) {
	            if(line.contains("Login DevMedia")) {
	                found = true;                
	            }
	        }
	        return !found;
	    }
	     
	    public BufferedReader criaBufferedReader (final HttpResponse response) throws IOException {
	        return new BufferedReader(new InputStreamReader(
	        		response.getEntity().getContent()));
	    }
	}
