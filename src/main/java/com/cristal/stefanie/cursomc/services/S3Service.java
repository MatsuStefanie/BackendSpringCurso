package com.cristal.stefanie.cursomc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;


    public void uploadFile(String localFilePath, String nomeDoArquivo) {
        try {
            File file = new File(localFilePath);
            LOG.info("Iniciando Upload...");
            s3client.putObject(new PutObjectRequest(bucketName, nomeDoArquivo, file));
            LOG.info("Update finalizado!");
        } catch (AmazonServiceException e) {
            LOG.info("Exceção da AmazonSeviceException em requisições PUT, devido:");
            LOG.info("Mensagem de erro:         " + e.getMessage());
            LOG.info("Código HTTP:              " + e.getStatusCode());
            LOG.info("Código de erro da AWS:    " + e.getErrorCode());
            LOG.info("Tipo do erro:             " + e.getErrorType());
            LOG.info("ID da requisição:         " + e.getRequestId());
        } catch (AmazonClientException e) {
            LOG.info("Exceção da AmazonClientException");
            LOG.info("Mensagem de erro:         " + e.getMessage());
        }
    }
}
