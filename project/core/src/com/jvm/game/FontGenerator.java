package com.jvm.game;


import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

//Used to generate different sized text
public class FontGenerator {

    public static BitmapFont GenerateFont(FileHandle font, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(font);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont ansFont = generator.generateFont(parameter);
        generator.dispose();
        return ansFont;
    }
}
