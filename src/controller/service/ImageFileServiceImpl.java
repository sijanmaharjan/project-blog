package controller.service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ImageFileServiceImpl implements ImageFileService {

    private static final String IMAGE_DIR = "images";
    private static final String IMG_EXTENSION = ".jpeg";

    @Override
    public void uploadFile(String blogId, HttpServletRequest request, Part filePart) throws IOException {
        File imagePath = getImageFile(getApplicationPath(request), "");
        createDirectory(imagePath);
        InputStream filecontent = filePart.getInputStream();
        BufferedImage bufferedImage = ImageIO.read(filecontent);
        if(bufferedImage != null) ImageIO.write(bufferedImage, "jpg", new File(imagePath, getFileName(blogId)));
    }

    @Override
    public void removeFile(HttpServletRequest request, String blogId) {
        String applicationPath = getApplicationPath(request);
        removeFile(applicationPath, blogId);
    }

    @Override
    public void removeFiles(HttpServletRequest request, List<String> blogIds) {
        String applicationPath = getApplicationPath(request);
        blogIds.forEach(blogId -> removeFile(applicationPath, blogId));
    }

    public void removeFile(String applicationPath, String blogId){
        String fileName = getFileName(blogId);
        deleteFile(getImagePath(applicationPath), fileName);
    }

    public void deleteFile(String path, String fileName){
        File file = getFile(path, fileName);
        if(file.exists()) file.delete();
    }

    public String getApplicationPath(HttpServletRequest request){
        return request.getServletContext().getRealPath("");
    }

    public String getImagePath(String applicationPath){
        return applicationPath + File.separator + IMAGE_DIR;
    }

    public File getImageFile(String applicationPath, String fileName){
        return getFile(getImagePath(applicationPath), fileName);
    }

    public String getFileName(String blogId){
        return blogId + IMG_EXTENSION;
    }

    public File getFile(String path, String fileName){
        return new File(path, fileName);
    }

    public void createDirectory(File path){
        if (!path.exists()) {
            path.mkdirs();
        }
    }
}
