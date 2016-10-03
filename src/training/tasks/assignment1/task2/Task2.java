package training.tasks.assignment1.task2;

public class Task2 {

	/*
	 * ��������� 2 ���������:
	 * - ���������� ��������� ������������������
	 * - ��������� �������
	 */
	public static void main(String[] args) {

		if (args.length == 2) {
			final int max = Integer.valueOf(args[0]);
			final double epsylon = Double.valueOf(args[1]);

			final double s[] = sequence(max);

			final int minIndex = minElementIndex(s, epsylon);

			System.out.println("����������� ������, ��������������� ������� M: " + minIndex
					+ "\n\n�������� ������������������:");

			for (int i = 0; i < s.length; i++) {
				System.out.printf("%3d %s %.9f%n", i, ":", s[i]);
			}
		} else {
			System.out.println("��������� 2 ���������");
		}
	}

	/*
	 * ���������� ������������ ������� �������� �������, 
	 * ���������������� �������: a(i) < epsylon, ��� i = 1, 2, ..., n
	 * @param sequence ������ ��������� ������������������
	 * @param epsylon ��������� �������
	 * @return ����������� ������ �������� �������, ���������������� ���������� �������
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
	 * ���������� ������������������ a(n) = 1 / (n + 1)^2
	 * @param n ���������� ��������� ������������������
	 * @return ������ ���������
	 */
	private static double[] sequence(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("���������� ��������� ������������������ ������ ���� > 0");
		}

		final double[] sequence = new double[n];
		for (int i = 0; i < n; i++) {
			sequence[i] = 1 / Math.pow(i + 1, 2);
		}

		return sequence;
	}
}
