package com.wipro.springboot.assignments.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;




@Table(name = "TODO")
@Entity
public class ToDo {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="ID")
		private long id;
		
		@JsonProperty("username")
		@Column(name="USERNAME")
		private String userName;
		
		@JsonProperty("taskname")
		@Column(name="TASKNAME")
		private String taskName;
		
		@JsonProperty("status")
		@Column(name="STATUS")
		private boolean status;

		
		public ToDo() {
			super();
		}

		public ToDo(long id, String userName, String taskName, boolean status) {
			super();
			this.id = id;
			this.userName = userName;
			this.taskName = taskName;
			this.status = status;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getTaskName() {
			return taskName;
		}

		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

}
