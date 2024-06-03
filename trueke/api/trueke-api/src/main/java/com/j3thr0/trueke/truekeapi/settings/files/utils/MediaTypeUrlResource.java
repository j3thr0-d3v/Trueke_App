package com.j3thr0.trueke.truekeapi.settings.files.utils;

import com.j3thr0.trueke.truekeapi.settings.files.exception.StorageException;
import org.apache.tika.Tika;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;

public class MediaTypeUrlResource extends UrlResource {

    public MediaTypeUrlResource(URL url) {
        super(url);
    }

    public MediaTypeUrlResource(URI uri) throws MalformedURLException {
        super(uri);
    }

    public MediaTypeUrlResource(String path) throws MalformedURLException {
        super(path);
    }

    public MediaTypeUrlResource(String protocol, String location) throws MalformedURLException {
        super(protocol, location);
    }

    public MediaTypeUrlResource(String protocol, String location, String fragment) throws MalformedURLException {
        super(protocol, location, fragment);
    }

    public String getType (){
        Tika t = new Tika();
        try{
            return t.detect(this.getFile());
        } catch(IOException ex){
            throw new StorageException("Error trying to get the MIME type", ex);
        }
    }
}
