package training.tasks.assignment1.task5;

public class Task5 {
	
	/*
	 * ��������� 1 ��������:
	 * - ����������� �������
	 */
	
	public static void main(String[] args) {
		
		if (args.length == 1) {
			final int n = Integer.valueOf(args[0]);
			
			if (n <= 1) {
				throw new IllegalArgumentException("����������� ������� ������ ���� > 1");
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.printf("%2d", (i == j || (i + j + 1) == n) ? 1 : 0);
				}
				System.out.println();
			}
			
		} else {
			System.out.println("��������� 1 ��������");
		}
	}
}
