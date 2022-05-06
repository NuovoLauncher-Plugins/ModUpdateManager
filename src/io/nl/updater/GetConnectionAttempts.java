package io.nl.updater;

import java.io.IOException;

import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.text.SimpleDateFormat;

import java.time.Duration;

import java.util.Date;
import java.util.TimeZone;



public class GetConnectionAttempts {
	private static String convert(long re) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		String formatted = sdf.format(new Date(re * 1_000L)); //Epoch times 1000 seconds
		return formatted;
	}
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static String[] getAttempts() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.github.com/users/JavaIsNotMagic"))
                .setHeader("User-Agent", "GithubConnectionsManagerBot") // add request header
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        int size = response.headers().allValues("x-ratelimit-remaining").size();
        int i = 0;
        String[] value = new String[2];
        
        for(i = 0; i < size; i++) {
        	value[i] = response.headers().allValues("x-ratelimit-remaining").get(i);
        	value[i + 1] = convert(Long.parseLong(response.headers().allValues("x-ratelimit-reset").get(i)));
        }
        return value;
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
    	String[] values = GetConnectionAttempts.getAttempts();
    	System.out.println("We can connect at: " + values[1]);
    
    }

}
