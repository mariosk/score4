package com.wgsdg.score4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score4Container<M, N> {
	private M first;
	private N second;
}
