package org.reflection.controller;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class _OithController {

    protected BigInteger faker(BigInteger id) {

        BigInteger di = new BigInteger(RequestContextHolder.currentRequestAttributes().getSessionId(), 16);
        return di.xor(id);
    }

    private static final String FLASH_ERROR_MESSAGE = "errorMessage";
    private static final String FLASH_FEEDBACK_MESSAGE = "feedbackMessage";
    private static final String VIEW_REDIRECT_PREFIX = "redirect:";

    protected static final String SEARCH_CRITERIA = "searchCriteria";
    protected static final String REQUEST_MAPPING_LIST = "index";
    protected static final String ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND = "error.message.deleted.not.found";
    protected static final String ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND = "error.message.edited.not.found";

    protected static final String FEEDBACK_MESSAGE_KEY_CREATED = "feedback.message.created";
    protected static final String FEEDBACK_MESSAGE_KEY_DELETED = "feedback.message.deleted";
    protected static final String FEEDBACK_MESSAGE_KEY_EDITED = "feedback.message.edited";
    @Resource
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    public void errorHandler(BindingResult bindingResult, Exception e) {

        if (e instanceof DuplicateKeyException) {
            DuplicateKeyException uuu = (DuplicateKeyException) e;

            String hhh = uuu.getCause().getMessage();

            System.out.println("err dup key: " + hhh);

            int kk = hhh.lastIndexOf(": \"");
            if (kk != -1) {
                hhh = hhh.substring(kk + 3, hhh.length() - 4);
            }

            ObjectError yyyy = new ObjectError(bindingResult.getObjectName(), "Duplicate record notification for value '" + hhh + "'");
            //FieldError yyyy=   new FieldError(bindingResult.getObjectName(), "code", e.getMessage()+" real val: "+ uuu.getRootCause()+" hhhh"+ val);

            bindingResult.addError(yyyy);
        } else if (e instanceof org.springframework.dao.DataIntegrityViolationException) {
            ObjectError yyyy = new ObjectError(bindingResult.getObjectName(), "Data Integrity Violation '" + ((org.springframework.dao.DataIntegrityViolationException) e).getMessage() + "'");
            //FieldError yyyy=   new FieldError(bindingResult.getObjectName(), "code", e.getMessage()+" real val: "+ uuu.getRootCause()+" hhhh"+ val);

            bindingResult.addError(yyyy);
        } else {
            System.out.println("errr 1116: " + e);
            System.out.println("errr class 1116: " + e.getClass().getSimpleName());
            ObjectError yyyy = new ObjectError(bindingResult.getObjectName(), "Info: " + e.getMessage() + "");
            bindingResult.addError(yyyy);
        }
    }

    /**
     * Adds a new error message
     *
     * @param model A model which stores the the error message.
     * @param code A message code which is used to fetch the correct message
     * from the message source.
     * @param params The parameters attached to the actual error message.
     */
    protected void addErrorMessage(RedirectAttributes model, String code, Object... params) {
        Locale current = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(code, params, current);
        model.addFlashAttribute(FLASH_ERROR_MESSAGE, localizedErrorMessage);
    }

    /**
     * Adds a new feedback message.
     *
     * @param model A model which stores the feedback message.
     * @param code A message code which is used to fetch the actual message from
     * the message source.
     * @param params The parameters which are attached to the actual feedback
     * message.
     */
    protected void addFeedbackMessage(RedirectAttributes model, String code, Object... params) {
        Locale current = LocaleContextHolder.getLocale();
        String localizedFeedbackMessage = messageSource.getMessage(code, params, current);
        model.addFlashAttribute(FLASH_FEEDBACK_MESSAGE, localizedFeedbackMessage);
    }

    @RequestMapping(value = "/getFile/{code}.{ext}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<byte[]> getFile(@PathVariable("code") String code, @PathVariable("ext") String ext, HttpServletRequest request) {
        System.out.println("finding getFile: code: " + code + " ext: " + ext);
        return getFile(request, "files", code, ext);
    }

    @RequestMapping(value = "/getPhoto/{code}.{format}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<byte[]> getPhoto(@PathVariable("code") String code, @PathVariable("format") String format, HttpServletRequest request) {
        System.out.println("finding getPhoto: code: " + code + " format: " + format);
        return getImage(request, "pics", code, format);
    }

    @RequestMapping(value = "/getPhotoTumb/{code}.{format}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<byte[]> getPhotoSmall(@PathVariable("code") String code, @PathVariable("format") String format, HttpServletRequest request) {
        System.out.println("finding getPhotoSmall: code: " + code + " format: " + format);
        return getImage(request, "tumb_pics", code, format);
    }

    ResponseEntity<byte[]> getFile(HttpServletRequest request, String dir, String code, String ext) {
        try {
            String parent = getOuterParentPath(request);
            File file = new File(parent + "\\repositories\\" + dir + "\\" + code + "." + ext);

            byte[] buf = IOUtils.toByteArray(new FileInputStream(file));
            System.out.println("size file: " + buf.length);
            return new ResponseEntity<>(buf, HttpStatus.CREATED);
        } catch (Exception e) {
            return null;
        }
    }

    ResponseEntity<byte[]> getImage(HttpServletRequest request, String dir, String code, String format) {
        try {
            String parent = getOuterParentPath(request);
            File file = new File(parent + "\\repositories\\" + dir + "\\" + code + "." + format);

            byte[] buf = IOUtils.toByteArray(new FileInputStream(file));
            System.out.println("size file: " + buf.length);
            final HttpHeaders headers = new HttpHeaders();
            if (format.toLowerCase().contains("jpg")) {
                headers.setContentType(MediaType.IMAGE_JPEG);
            } else if (format.toLowerCase().contains("gif")) {
                headers.setContentType(MediaType.IMAGE_GIF);
            } else if (format.toLowerCase().contains("png")) {
                headers.setContentType(MediaType.IMAGE_PNG);
            }
            return new ResponseEntity<>(buf, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return null;
        }
    }

    protected String multipartFileHandler(MultipartHttpServletRequest request, String col) {

        MultipartFile multipartFile = request.getFile(col + "OBJ");

        if ((multipartFile != null && multipartFile.getSize() > 0)) {

            try {
                String fileCaption = multipartFile.getOriginalFilename();
                //File contx = new File(request.getServletContext().getRealPath("/"));
//                String root=request.getServletContext().getRealPath("/");

                String parent = getOuterParentPath(request);
                File file = new File(parent
                        + File.separator
                        + "repositories"
                        + File.separator
                        + "files"
                        + File.separator
                        + fileCaption);

                FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
                System.out.println("Go to the location:  " + file.toString() + " on your computer has been stored.");
                // currObject.setPicFile("/pics/" + fileCaption);
                //return "/" + dir + "/" + fileCaption;
                return fileCaption;
            } catch (Exception e) {
                System.out.println("set " + col + " err: " + e);
                //currObject.setPicFile("err: " + e);
                return "err: " + e;
            }
        }
        return "";
    }

    protected String multipartImageFileHandler(MultipartHttpServletRequest request, String col) {

        System.out.println("hhhhhhhhhhh 1025:" + col);
        //MultipartFile picFileOBJ = request.getFile("picFileOBJ");
        MultipartFile multipartFile = request.getFile(col + "OBJ");

        if (multipartFile != null && multipartFile.getSize() > 0) {

            try {
                //String fileCaption = multipartFile.getOriginalFilename();

                String jjj = multipartFile.getContentType();
                System.out.println("content type : " + jjj);

                String extx = "jpg";

                if (jjj.contains("jpeg")) {
                    extx = "jpg";
                } else if (jjj.contains("png")) {
                    extx = "png";
                } else if (jjj.contains("gif")) {
                    extx = "gif";
                }

                String idOne = UUID.randomUUID().toString();

                String fileCaption = idOne + "." + extx;

                String parent = getOuterParentPath(request);
                File file = new File(parent
                        + File.separator
                        + "repositories"
                        + File.separator
                        + "pics"
                        + File.separator
                        + fileCaption);

                File filet = new File(parent
                        + File.separator
                        + "repositories"
                        + File.separator
                        + "tumb_pics"
                        + File.separator
                        + fileCaption);

                if (!filet.getParentFile().exists()) {
                    filet.getParentFile().mkdirs();
                }

                FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());

                BufferedImage bsrc = ImageIO.read(multipartFile.getInputStream());//  new File(data));
                BufferedImage bdest = new BufferedImage(45, 55, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = bdest.createGraphics();
                AffineTransform at = AffineTransform.getScaleInstance((double) 45 / bsrc.getWidth(),
                        (double) 55 / bsrc.getHeight());
                g.drawRenderedImage(bsrc, at);

                ImageIO.write(bdest, extx, filet);

                //FileUtils.writeByteArrayToFile(filet, multipartFile.getBytes());
                System.out.println("Go to the location:  " + file.toString() + " on your computer and verify that the has been stored.");
                // currObject.setPicFile("/pics/" + fileCaption);
                //return "/" + dir + "/" + fileCaption;
                return fileCaption;
            } catch (Exception e) {
                System.out.println("set " + col + " err: " + e);
                //currObject.setPicFile("err: " + e);
                return "err: " + e;
            }
        }
        return "";
    }

    /**
     * Creates a redirect view path for a specific controller action
     *
     * @param path The path processed by the controller method.
     * @return A redirect view path to the given controller method.
     */
    protected String createRedirectViewPath(String path) {
        StringBuilder builder = new StringBuilder();
        builder.append(VIEW_REDIRECT_PREFIX);
        builder.append(path);
        return builder.toString();
    }

    protected String getOuterParentPath(HttpServletRequest request) {
        File root = new File(request.getServletContext().getRealPath("/"));
        File rootUp = new File(root.getParentFile() + File.separator + root.getName() + "_repo");

        System.out.println("root yaa: " + rootUp);

        return rootUp.toString();
    }
}
