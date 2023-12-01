package com.cr.game.data;

public class GameData {

    // Konstanta yang menentukan ukuran setiap ubin (tile) dalam permainan. Nilai tetap 32x32 piksel.
    public static final int TILE_SIZE = 32;

    // Konstanta yang mewakili arah ke kiri. Nilai diatur ke -1 untuk memudahkan penggunaan dalam logika pergerakan ke kiri.
    public static final int LEFT = -1;

    // Konstanta yang mewakili arah ke kanan. Nilai diatur ke 1 untuk memudahkan penggunaan dalam logika pergerakan ke kanan.
    public static final int RIGHT = 1;

    // --> Konstanta yang menentukan berapa poin yang diberikan setiap kali pemain memiliki satu nyawa (life) tambahan. Poin ini akan ditambahkan ke skor akhir.
    public static final int POINTS_PER_LIFE = 150;

    // Menyimpan skor permainan. Variabel ini dapat diakses secara statis dan diberi nilai awal 0.
    public static int score = 0;
}
