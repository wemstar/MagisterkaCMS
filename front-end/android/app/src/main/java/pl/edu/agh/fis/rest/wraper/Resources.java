/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.edu.agh.fis.rest.wraper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * General helper to easily create a wrapper for a collection of entities.
 *
 * @author Oliver Gierke
 */
@JsonIgnoreProperties({"_links"})
public class Resources<T> extends ResourceSupport implements Iterable<T> {

 	@JsonProperty ("_embedded")
 	private final Map<String,Collection<T>> content;

	/**
	 * Creates an empty {@link Resources} instance.
	 */
	public Resources() {
		content = new HashMap<>();
	}

	/**
	 * Creates a {@link Resources} instance with the given content and {@link org.springframework.hateoas.Link}s (optional).
	 *
	 * @param content must not be {@literal null}.
	 * @param links the links to be added to the {@link Resources}.
	 */
	/*public Resources(Iterable<T> content, Link... links) {
		this(content, Arrays.asList(links));
	}*/

	/**
	 * Creates a {@link Resources} instance with the given content and {@link org.springframework.hateoas.Link}s.
	 *
	 * @param content must not be {@literal null}.
	 * @param links the links to be added to the {@link Resources}.
	 */
	/*public Resources(Iterable<T> content, Iterable<Link> links) {

		Assert.notNull(content);

		this.content = new HashMap<>();

		for (T element : content) {
			this.content.add(element);
		}
		this.add(links);
	}*/

	/**
	 * Creates a new {@link Resources} instance by wrapping the given domain class instances into a {@link org.springframework.hateoas.Resource}.
	 *
	 * @param content must not be {@literal null}.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	/*public static <T extends Resource<S>, S> Resources<T> wrap(Iterable<S> content) {

		Assert.notNull(content);
		ArrayList<T> resources = new ArrayList<T>();

		for (S element : content) {
			resources.add((T) new Resource<S>(element));
		}

		return new Resources<T>(resources);
	}*/

	/**
	 * Returns the underlying elements.
	 *
	 * @return the content will never be {@literal null}.
	 */
	public Collection<T> getContent() {
		ArrayList<T> list = new ArrayList<>();
		for (Collection<T> col:content.values()) {
			list.addAll(col);
		}
		return Collections.unmodifiableCollection(list);
	}

	/* 
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return getContent().iterator();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.hateoas.ResourceSupport#toString()
	 */
	@Override
	public String toString() {
		return String.format("Resources { content: %s, %s }", getContent(), super.toString());
	}

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.hateoas.ResourceSupport#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		}

		if (obj == null || !obj.getClass().equals(getClass())) {
			return false;
		}

		Resources<?> that = (Resources<?>) obj;

		boolean contentEqual = this.content == null ? that.content == null : this.content.equals(that.content);
		return contentEqual ? super.equals(obj) : false;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.hateoas.ResourceSupport#hashCode()
	 */
	@Override
	public int hashCode() {

		int result = super.hashCode();
		result += content == null ? 0 : 17 * content.hashCode();

		return result;
	}
}
