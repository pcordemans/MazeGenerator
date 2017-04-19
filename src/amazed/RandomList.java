package amazed;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomList<T> extends ArrayList<T>{
	
	private static final long serialVersionUID = -3851490836545904119L;

	public T removeRandom(){
		int randomIndex = ThreadLocalRandom.current().nextInt(0, size());
		return remove(randomIndex);
	}
}
