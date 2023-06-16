package project.lesson.dto.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.lesson.entity.message.Message;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MessageDto {

    @ApiModelProperty(example = "제목", value = "제목", required = true)
    @NotNull(message = "제목을 입력해주세요")
    @Max(value = 110, message = "제목의 Byte 길이가 110 이하여야 합니다")
    private String title;

    @ApiModelProperty(example = "내용", value = "내용", required = true)
    @NotNull(message = "내용을 입력해주세요")
    @Max(value = 1120, message = "내용의 Byte 길이가 1120 이하여야 합니다")
    private String content;

    @ApiModelProperty(example = "보낸 사람 ID", value = "보낸 사람 ID")
    private String senderId;

    @ApiModelProperty(example = "보낸 사람 닉네임", value = "보낸 사람 닉네임")
    private String senderNickname;

    @ApiModelProperty(example = "받는 사람 ID", value = "받는 사람 ID")
    private String receiverId;

    @ApiModelProperty(example = "받는 사람 닉네임", value = "받는 사람 닉네임", required = true)
    @NotNull(message = "받는 사람 닉네임 입력해주세요")
    @Max(value = 50, message = "받는 사람 닉네임의 Byte 길이가 50 이하여야 합니다")
    private String receiverNickname;

    // entity 를 dto 변환
    public MessageDto(Message entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.senderId = entity.getSender().getId();
        this.senderNickname = entity.getSender().getNickname();
        this.receiverId = entity.getReceiver().getId();
        this.receiverNickname = entity.getReceiver().getNickname();
    }
}