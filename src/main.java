public class main {

	public static class node implements Comparable<node> {
		int x;

		node(int x) {
			this.x = x;
		}

		@Override
		public int compareTo(node o) {
			if (o.x < x)
				return 1;
			else
				return -1;
		}

	}

	public static void main(String[] args) {
		priorityQueue<node> q = new priorityQueue();
		node e=new node(5);
		q.insert(e);
		q.insert(new node(2));
		q.insert(new node(8));
		q.insert(new node(10));
		q.insert(new node(1000));
		q.insert(new node(100));
		System.out.println(q.contains(e));
		
		System.out.println(q.size());
		System.out.println(q.isEmpty());
		while (!q.isEmpty()) {
			System.out.println(q.poll().x);
		}
	//	System.out.println(q.poll());
		System.out.println(q.isEmpty());

	}

}
