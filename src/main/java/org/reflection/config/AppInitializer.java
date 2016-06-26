package org.reflection.config;

import java.io.File;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }

    private MultipartConfigElement getMultipartConfigElement() {

        String tempFilePath = null;
        try {
            File temp = File.createTempFile("temp-file-name", ".tmp");

            System.out.println("Temp file : " + temp.getAbsolutePath());

            //Get tempropary file path
            String absolutePath = temp.getAbsolutePath();
            tempFilePath = absolutePath.
                    substring(0, absolutePath.lastIndexOf(File.separator));

            System.out.println("Temp file path : " + tempFilePath);

//            String property = "java.io.tmpdir";
//
//            String tempDir = System.getProperty(property);
//            System.out.println("OS current temporary directory is " + tempDir);
        } catch (Exception e) {
        }

        //File yty = hh.getParentFile();
        System.out.println("yuop file path temp yaa 785: " + tempFilePath);

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(tempFilePath);

        //MultipartConfigElement multipartConfigElement = new MultipartConfigElement( LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }
}
