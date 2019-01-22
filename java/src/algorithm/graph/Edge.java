package algorithm.graph;

/**
 * A->B的一条线路
 */
public class Edge {
	private Vertex v1;
	private Vertex v2;
	private Integer length;

	public Vertex getV1() {
		return v1;
	}

	public void setV1(Vertex v1) {
		this.v1 = v1;
	}

	public Vertex getV2() {
		return v2;
	}

	public void setV2(Vertex v2) {
		this.v2 = v2;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Edge createByString(String str) {
		try {
			str = str.trim();
			this.v1 = new Vertex(str.charAt(0));
			this.v2 = new Vertex(str.charAt(1));

			char last = str.charAt(str.length() - 1);
			if (last >= '0' && last <= '9') {
				this.length = Integer.parseInt(str.substring(2, str.length()));
			} else {
				// 逗号结尾
				this.length = Integer.parseInt(str.substring(2,
						str.length() - 1));
			}
		} catch (Exception e) {
			// do nothing
		}
		return this;
	}

	/**
	 * 判断当前线路是否正确
	 * @return boolean
	 */
	public boolean isRightLine() {
        return !(v1 == null || v2 == null || length == null || v1.equals(v2));
	}

	@Override
	public String toString() {
		return "Edge [v1=" + v1 + ", v2=" + v2 + ", length=" + length
				+ "]";
	}
}
