package com.jvm.game;


import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Used to interface with FreeType library to generate different sized fonts
 *
 * <p>Generates fonts on the fly with a specific size</p>
 */
public class FontGenerator {

    /**
     * Generates the font
     * @param font The font style to be used
     * @param size The size to be generated
     * @return The generated font
     */
    public static BitmapFont GenerateFont(FileHandle font, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(font);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont ansFont = generator.generateFont(parameter);
        generator.dispose();
        return ansFont;
    }
}
