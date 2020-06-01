public class ThreadsCompare {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];
    static float[] arr1 = new float[h];
    static float[] arr2 = new float[h];

    public static void changeArray(float[] arr, int id) {
        System.out.println(id + "-й поток запущен.");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(id + "-й поток завершен.");
    }

    public static void calcTime(long startTime) {
        long finishTime = System.currentTimeMillis();
        System.out.println("Обработка массива в два потока завершена. Время обработки: " + (finishTime - startTime) + " мс.");
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

// Обработка в 1 поток
        System.out.println("Начинаем обработку массива в один поток.");
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long finishTime1 = System.currentTimeMillis();
        System.out.println("Обработка массива в один поток завершена. Время обработки: " + (finishTime1 - startTime1) + " мс.");

// Обработка в 2 потока
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        System.out.println("Начинаем обработку массива в два потока.");
        long startTime2 = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        Thread t1 = new Thread(() -> changeArray(arr1, 1));
        Thread t2 = new Thread(() -> changeArray(arr2, 2));
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        Thread calc = new Thread(() -> calcTime(startTime2));
        calc.start();
        calc.join();
    }
}
