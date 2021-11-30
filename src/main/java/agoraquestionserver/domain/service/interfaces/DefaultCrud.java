package agoraquestionserver.domain.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DefaultCrud<T> {

	public T save (T t);
	
	public T find(Long id);
	
	public Page<T> listAll(Pageable pageable);
	
	public void delete(Long id);
}
