package main;

public class bookResults {
	private String name;
	private int id;
	private int parentId;
	public bookResults(String name, int id)  {
		this.name = name;
		this.id = id;
	}
	public bookResults(String name, int id, int parentId)  {
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		bookResults other = (bookResults) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
