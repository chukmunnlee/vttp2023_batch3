package vttp2023.batch3.paf.day29.services;

import org.springframework.stereotype.Service;

@Service
public class CounterService {

	private int count = 0;

	public int inc() {
		return inc(1);
	}

	public int inc(int del) {
		count += del;
		return count;
	}

	public int get() {
		return inc(0);
	}
}
