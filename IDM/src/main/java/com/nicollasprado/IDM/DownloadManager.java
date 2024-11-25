package com.nicollasprado.IDM;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class DownloadManager {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o link do download");
        String inputURL = input.next();

        // Instanciando a URL
        URI downloadURI = new URI(inputURL);
        URL downloadURL = downloadURI.toURL();

        // Checando o tipo MIME do arquivo via requisiçao HTTP no cabeçalho Content-Type
        try {
            HttpURLConnection httpConn = (HttpURLConnection) downloadURL.openConnection();
            httpConn.setRequestMethod("HEAD");

            String fileType = httpConn.getContentType();
            System.out.println(fileType);

            httpConn.disconnect();
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            // Entrada dos dados com openStream
            InputStream dataIn = downloadURL.openStream();

            // Criando o arquivo no dispostivo local
            System.out.println("Especifique o local de download");
            String downloadDestination = input.next();
            FileOutputStream dataOut = new FileOutputStream(downloadDestination + "/downloadedFile");

            // Buffer para servir de espaço temporario de transporte de dados
            byte[] buffer = new byte[1024];

            // lendo e gravando as informaçoes
            int bytesRead;
            while((bytesRead = dataIn.read(buffer)) != -1){
                dataOut.write(buffer, 0, bytesRead);
            }

            dataIn.close();
            dataOut.close();
            System.out.println("DOwnload realizado!");
        }catch (IOException exc){
            System.out.println(exc.getMessage());
        }
    }
}
