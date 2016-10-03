package training.tasks.assignment1.task3;

public class Task3 {

	/*
	 * ��������� 3 ���������: 
	 * - ������ ������� ��������� 
	 * - ������� ������� ��������� 
	 * - ���
	 */
	public static void main(String[] args) {

		if (args.length == 3) {
			final double lowerLimit = Double.valueOf(args[0]);
			final double upperLimit = Double.valueOf(args[1]);
			final double step = Double.valueOf(args[2]);

			if (upperLimit < lowerLimit) {
				throw new IllegalArgumentException("������� ������� ������ ���� ������ ������ �������");
			}

			System.out.printf("%8s %5s %10s%n", "x", "|", "f(x)");

			for (double i = lowerLimit; i <= upperLimit; i += step) {
				System.out.printf("%8.3f %5s %10.4f%n", i, "|", f(i));
			}

		} else {
			System.out.println("��������� 3 ���������");
		}
	}

	/*
	 * ���������� ������� f(x) = tg(2x) - 3
	 * 
	 * @param x �������� �������
	 * 
	 * @return �������� �������
	 */
	private static double f(double x) {
		return Math.tan(Math.toRadians(2 * x)) - 3;
	}

}