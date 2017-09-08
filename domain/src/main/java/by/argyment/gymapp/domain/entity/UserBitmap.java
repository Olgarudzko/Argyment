package by.argyment.gymapp.domain.entity;

import java.io.File;

/**
 * @author Olga Rudzko
 */

public class UserBitmap implements EntityModel {

    private File file;
    private String name;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
