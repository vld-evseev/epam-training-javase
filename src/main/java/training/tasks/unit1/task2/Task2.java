package training.tasks.unit1.task2;

public class Task2 {

    /*
     * Требуется 2 аргумента:
     * - количество элементов последовательности
     * - граничное условие
     */
    public static void main(String[] args) {

        if (args.length == 2) {
            final int max = Integer.valueOf(args[0]);
            final double epsylon = Double.valueOf(args[1]);

            final double s[] = sequence(max);

            final int minIndex = minElementIndex(s, epsylon);

            System.out.println("Минимальный индекс, удовлетворяющий условию M: " + minIndex
                    + "\n\nЭлементы последовательности:");

            for (int i = 0; i < s.length; i++) {
                System.out.printf("%3d %s %.9f%n", i, ":", s[i]);
            }
        } else {
            System.out.println("Требуется 2 аргумента");
        }
    }

    /*
     * Вычивление минимального индекса элемента массива,
     * удовлетворяющего условию: a(i) < epsylon, где i = 1, 2, ..., n
     * @param sequence массив элементов последовательности
     * @param epsylon граничное условие
     * @return минимальный индекс элемента массива, удовлетворяющего граничному условию
     *
     */
    private static int minElementIndex(double[] sequence, double epsylon) {
        int i = 0;

        while (sequence[i] >= epsylon) {
            i++;
        }

        return i;
    }

    /*
     * Вычисление последовательности a(n) = 1 / (n + 1)^2
     * @param n количество элементов последовательности
     * @return массив элементов
     */
    private static double[] sequence(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Количество элементов последовательности должно быть > 0");
        }

        final double[] sequence = new double[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = 1 / Math.pow(i + 1, 2);
        }

        return sequence;
    }
}
