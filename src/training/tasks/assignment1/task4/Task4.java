package training.tasks.assignment1.task4;

public class Task4 {

	/*
	 * �� ���� �������� ���������� ���������� ����������
	 * � ���� �������� ��������
	 */
	public static void main(String[] args) {
		
		if (args.length > 1) {
			final double a[] = new double[args.length];
			
			for (int i = 0; i < a.length; i++) {
				a[i] = Double.valueOf(args[i]);
			}
			
			System.out.println("���������: " + max(a));
		} else {
			System.out.println("��������� ��� ������� 1 ��������");
		}
	}
	
	
	/*
	 * ���� ������������������: 
	 * a(1) + a(2*n), a(2) + a(2*n-1) ,..., a(n) + a(n + 1)
	 * 
	 * ������ �������, ��������������� ������ ������������������
	 * f(x) = sqr(x)
	 * 
	 * ������ ����� ��������� ������� f(x), ���������� ������� ��������
	 * ������� ������� � ���������� ������������ �������� ������� ����� 
	 * ���� ���������
	 * 
	 * @param array ������ ���������
	 * 
	 * @return ������������ �������� ������� f(x)
	 */
	
	private static double max(double[] array) {

		double max = Double.NEGATIVE_INFINITY;
		
		for (int i = 0; i < array.length; i++) {
			double fx = Math.pow(array[i], 2);
			
			if (fx > max) {
				max = fx;
			}
		}
		
		return max;
	}

}
