package com.profitkey.stock.dto.response.community;

import java.time.LocalDateTime;

import com.profitkey.stock.entity.Community;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommunityResponse {
	private final String id;
	private final Long writerId;
	private final String parentId;
	private final String content;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private long likeCount;
	private long replieCount;

	public static CommunityResponse fromEntity(Community community, long likeCount, long replieCount) {
		return CommunityResponse.builder()
			.id(community.getId())
			.writerId(community.getWriterId())
			.parentId(community.getParentId())
			.content(community.getContent())
			.createdAt(community.getCreatedAt())
			.updatedAt(community.getUpdatedAt())
			.likeCount(likeCount)
			.replieCount(replieCount)
			.build();
	}

}
