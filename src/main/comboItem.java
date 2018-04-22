package main;

public class comboItem {
	private String name;
	private int id;
	private int parentId;
	public comboItem(String name, int id)  {
		this.name = name;
		this.id = id;
	}
	public comboItem(String name, int id, int parentId)  {
		this.name = name;
		this.id = id;
		this.parentId = parentId;
	}
	
	public String toString(){
		return name;
	}
	public String getName(){
		return name;
	}
	public int getId(){
		return id;
	}
	public int getParentId(){
		return parentId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		comboItem other = (comboItem) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
}
