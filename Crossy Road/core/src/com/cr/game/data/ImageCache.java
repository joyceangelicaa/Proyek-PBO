package com.cr.game.data;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageCache {

    // Mendeklarasikan variabel statis atlas dari tipe TextureAtlas. Objek TextureAtlas digunakan untuk mengelola kumpulan tekstur yang diambil dari satu atau beberapa file gambar (spritesheet).
    public static TextureAtlas atlas;

    // Memuat tekstur dari file atlas.
    public static void load () {
        // Mendeklarasikan nama file atlas yang akan dimuat. Nama file ini digunakan untuk membuat objek TextureAtlas.
        String textureFile = "assets/data.txt";
        // Membuat objek TextureAtlas dari file atlas yang disebutkan. Gdx.files.internal digunakan untuk mendapatkan referensi file internal dalam framework LibGDX.
        atlas = new TextureAtlas(Gdx.files.internal(textureFile), Gdx.files.internal("assets/"));
    }

    // Mendapatkan TextureRegion berdasarkan nama tekstur.
    public static TextureRegion getTexture (String name) {
        return atlas.findRegion(name);  // --> Mengembalikan objek TextureRegion yang sesuai dengan nama yang diberikan dari atlas. TextureRegion adalah bagian dari TextureAtlas yang mengandung informasi koordinat dan ukuran suatu gambar atau tekstur di dalam atlas.
    }
}
