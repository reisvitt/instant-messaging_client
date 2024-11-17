package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Chat {
  private ObservableList<Message> messages;
  private String groupName;

  public Chat(String groupName) {
    this.groupName = groupName;
    this.messages = FXCollections.observableArrayList();
  }

  public ObservableList<Message> getMessages() {
    return messages;
  }

  public void setMessages(ObservableList<Message> messages) {
    this.messages = messages;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public Message getLastMessage() {
    if (messages.size() == 0) {
      return null;
    }

    Message message = messages.get(messages.size() - 1);

    return message;
  }

  public void addMessage(Message message) {
    messages.add(message);
  }
}