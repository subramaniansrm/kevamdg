package com.kevamdg.sr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/properties/mail.properties")
public class MailMessages {

	@Value("${requestCode}")
	private String requestCode;
	
	@Value("${roomBookingCode}")
	private String roomBookingCode;
	
	@Value("${roomBookingName}")
	private String roomBookingName;
	
	@Value("${name}")
	private String name;
	
	@Value("${requestType}")
	private String requestType;
	
	@Value("${requestSubType}")
	private String requestSubType;
	
	@Value("${requestDetail}")
	private String requestDetail;
	
	@Value("${requestSubject}")
	private String requestSubject;
	
	@Value("${roomSubject}")
	private String roomSubject;
	
	@Value("${requestRaise}")
	private String requestRaise;
	
	@Value("${requestResolve}")
	private String requestResolve;
	
	@Value("${requestReject}")
	private String requestReject;
	
	@Value("${requestResubmit}")
	private String requestResubmit;
	
	@Value("${and}")
	private String and;
	
	@Value("${on}")
	private String on;
	
	@Value("${bestRegards}")
	private String bestRegards;
	
	@Value("${supportTeam}")
	private String supportTeam;
	
	@Value("${dear}")
	private String dear;
	
	@Value("${smtpHost}")
	private String smtpHost;
	
	@Value("${smtpUserName}")
	private String smtpUserName;
	
	@Value("${smtpPassword}")
	private String smtpPassword;
	
	@Value("${fromMailAddress}")
	private String fromMailAddress;

	@Value("${defaultMail}")
	private String defaultMail;
	
	@Value("${escalation}")
	private int escalation;
	
	@Value("${escalationBefore}")
	private int escalationBefore;
	
	@Value("${escalationBeforeApproval}")
	private int escalationBeforeApproval;
	
	@Value("${escalationBeforeResolver}")
	private int escalationBeforeResolver;
	
	@Value("${beforeEscalationMailMinutes}")
	private int beforeEscalationMailMinutes;
	
	@Value("${mailPendingList}")
	private int mailPendingList;
	
	@Value("${approval}")
	private int approval;
	
	@Value("${resolver}")
	private int resolver;
	
	@Value("${request}")
	private int request;
	
	@Value("${requestUpdate}")
	private int requestUpdate;
	
	@Value("${approved}")
	private int approved;
	
	@Value("${roomBookingApproved}")
	private int roomBookingApproved;
	
	@Value("${roomBookingApproval}")
	private int roomBookingApproval;
	
	@Value("${rejected}")
	private int rejected;
	
	@Value("${roomBookingRejected}")
	private int roomBookingRejected;
	
	@Value("${resubmit}")
	private int resubmit;
	
	@Value("${roomBookingResubmit}")
	private int roomBookingResubmit;
	
	@Value("${completed}")
	private int completed;
	
	@Value("${inprogress}")
	private int inprogress;
	
	@Value("${reassign}")
	private int reassign;
	
	@Value("${reassignUser}")
	private int reassignUser;
	
	@Value("${reopenExecuter}")
	private int reopenExecuter;
	
	@Value("${reopen}")
	private int reopen;
	
	@Value("${close}")
	private int close;
	
	@Value("${cancel}")
	private int cancel;
	
	@Value("${escalationPendingRequest}")
	private int escalationPendingRequest;
	
	@Value("${escalationResolverRequest}")
	private int escalationResolverRequest;
	
	@Value("${roomIsBookedUnderYour}")
	private String roomIsBookedUnderYour;
	
	@Value("${meetingRoom}")
	private String meetingRoom;
	
	
	/**
	 * @return the defaultMail
	 */
	public String getDefaultMail() {
		return defaultMail;
	}

	/**
	 * @param defaultMail the defaultMail to set
	 */
	public void setDefaultMail(String defaultMail) {
		this.defaultMail = defaultMail;
	}

	/**
	 * @return the smtpHost
	 */
	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * @param smtpHost the smtpHost to set
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * @return the smtpUserName
	 */
	public String getSmtpUserName() {
		return smtpUserName;
	}

	/**
	 * @param smtpUserName the smtpUserName to set
	 */
	public void setSmtpUserName(String smtpUserName) {
		this.smtpUserName = smtpUserName;
	}

	/**
	 * @return the smtpPassword
	 */
	public String getSmtpPassword() {
		return smtpPassword;
	}

	/**
	 * @param smtpPassword the smtpPassword to set
	 */
	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	/**
	 * @return the fromMailAddress
	 */
	public String getFromMailAddress() {
		return fromMailAddress;
	}

	/**
	 * @param fromMailAddress the fromMailAddress to set
	 */
	public void setFromMailAddress(String fromMailAddress) {
		this.fromMailAddress = fromMailAddress;
	}

	/**
	 * @return the request
	 */
	public int getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(int request) {
		this.request = request;
	}

	/**
	 * @return the approved
	 */
	public int getApproved() {
		return approved;
	}

	/**
	 * @param approved the approved to set
	 */
	public void setApproved(int approved) {
		this.approved = approved;
	}

	/**
	 * @return the rejected
	 */
	public int getRejected() {
		return rejected;
	}

	/**
	 * @param rejected the rejected to set
	 */
	public void setRejected(int rejected) {
		this.rejected = rejected;
	}

	/**
	 * @return the resubmit
	 */
	public int getResubmit() {
		return resubmit;
	}

	/**
	 * @param resubmit the resubmit to set
	 */
	public void setResubmit(int resubmit) {
		this.resubmit = resubmit;
	}

	/**
	 * @return the completed
	 */
	public int getCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(int completed) {
		this.completed = completed;
	}

	/**
	 * @return the inprogress
	 */
	public int getInprogress() {
		return inprogress;
	}

	/**
	 * @param inprogress the inprogress to set
	 */
	public void setInprogress(int inprogress) {
		this.inprogress = inprogress;
	}

	/**
	 * @return the reassign
	 */
	public int getReassign() {
		return reassign;
	}

	/**
	 * @param reassign the reassign to set
	 */
	public void setReassign(int reassign) {
		this.reassign = reassign;
	}

	/**
	 * @return the reassignUser
	 */
	public int getReassignUser() {
		return reassignUser;
	}

	/**
	 * @param reassignUser the reassignUser to set
	 */
	public void setReassignUser(int reassignUser) {
		this.reassignUser = reassignUser;
	}

	/**
	 * @return the reopenExecuter
	 */
	public int getReopenExecuter() {
		return reopenExecuter;
	}

	/**
	 * @param reopenExecuter the reopenExecuter to set
	 */
	public void setReopenExecuter(int reopenExecuter) {
		this.reopenExecuter = reopenExecuter;
	}

	/**
	 * @return the reopen
	 */
	public int getReopen() {
		return reopen;
	}

	/**
	 * @param reopen the reopen to set
	 */
	public void setReopen(int reopen) {
		this.reopen = reopen;
	}

	/**
	 * @return the close
	 */
	public int getClose() {
		return close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(int close) {
		this.close = close;
	}

	/**
	 * @return the escalationPendingRequest
	 */
	public int getEscalationPendingRequest() {
		return escalationPendingRequest;
	}

	/**
	 * @param escalationPendingRequest the escalationPendingRequest to set
	 */
	public void setEscalationPendingRequest(int escalationPendingRequest) {
		this.escalationPendingRequest = escalationPendingRequest;
	}

	/**
	 * @return the escalationResolverRequest
	 */
	public int getEscalationResolverRequest() {
		return escalationResolverRequest;
	}

	/**
	 * @param escalationResolverRequest the escalationResolverRequest to set
	 */
	public void setEscalationResolverRequest(int escalationResolverRequest) {
		this.escalationResolverRequest = escalationResolverRequest;
	}

	/**
	 * @return the requestUpdate
	 */
	public int getRequestUpdate() {
		return requestUpdate;
	}

	/**
	 * @param requestUpdate the requestUpdate to set
	 */
	public void setRequestUpdate(int requestUpdate) {
		this.requestUpdate = requestUpdate;
	}

	/**
	 * @return the mailPendingList
	 */
	public int getMailPendingList() {
		return mailPendingList;
	}

	/**
	 * @param mailPendingList the mailPendingList to set
	 */
	public void setMailPendingList(int mailPendingList) {
		this.mailPendingList = mailPendingList;
	}

	/**
	 * @return the cancel
	 */
	public int getCancel() {
		return cancel;
	}

	/**
	 * @param cancel the cancel to set
	 */
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	/**
	 * @return the requestCode
	 */
	public String getRequestCode() {
		return requestCode;
	}

	/**
	 * @param requestCode the requestCode to set
	 */
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}

	/**
	 * @return the requestSubject
	 */
	public String getRequestSubject() {
		return requestSubject;
	}

	/**
	 * @param requestSubject the requestSubject to set
	 */
	public void setRequestSubject(String requestSubject) {
		this.requestSubject = requestSubject;
	}

	/**
	 * @return the and
	 */
	public String getAnd() {
		return and;
	}

	/**
	 * @param and the and to set
	 */
	public void setAnd(String and) {
		this.and = and;
	}

	/**
	 * @return the requestRaise
	 */
	public String getRequestRaise() {
		return requestRaise;
	}

	/**
	 * @param requestRaise the requestRaise to set
	 */
	public void setRequestRaise(String requestRaise) {
		this.requestRaise = requestRaise;
	}

	/**
	 * @return the bestRegards
	 */
	public String getBestRegards() {
		return bestRegards;
	}

	/**
	 * @param bestRegards the bestRegards to set
	 */
	public void setBestRegards(String bestRegards) {
		this.bestRegards = bestRegards;
	}

	/**
	 * @return the supportTeam
	 */
	public String getSupportTeam() {
		return supportTeam;
	}

	/**
	 * @param supportTeam the supportTeam to set
	 */
	public void setSupportTeam(String supportTeam) {
		this.supportTeam = supportTeam;
	}

	/**
	 * @return the approval
	 */
	public int getApproval() {
		return approval;
	}

	/**
	 * @param approval the approval to set
	 */
	public void setApproval(int approval) {
		this.approval = approval;
	}

	/**
	 * @return the resolver
	 */
	public int getResolver() {
		return resolver;
	}

	/**
	 * @param resolver the resolver to set
	 */
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	/**
	 * @return the dear
	 */
	public String getDear() {
		return dear;
	}

	/**
	 * @param dear the dear to set
	 */
	public void setDear(String dear) {
		this.dear = dear;
	}

	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**
	 * @return the requestSubType
	 */
	public String getRequestSubType() {
		return requestSubType;
	}

	/**
	 * @param requestSubType the requestSubType to set
	 */
	public void setRequestSubType(String requestSubType) {
		this.requestSubType = requestSubType;
	}

	/**
	 * @return the requestDetail
	 */
	public String getRequestDetail() {
		return requestDetail;
	}

	/**
	 * @param requestDetail the requestDetail to set
	 */
	public void setRequestDetail(String requestDetail) {
		this.requestDetail = requestDetail;
	}

	/**
	 * @return the requestResolve
	 */
	public String getRequestResolve() {
		return requestResolve;
	}

	/**
	 * @param requestResolve the requestResolve to set
	 */
	public void setRequestResolve(String requestResolve) {
		this.requestResolve = requestResolve;
	}

	/**
	 * @return the requestReject
	 */
	public String getRequestReject() {
		return requestReject;
	}

	/**
	 * @param requestReject the requestReject to set
	 */
	public void setRequestReject(String requestReject) {
		this.requestReject = requestReject;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the meetingRoom
	 */
	public String getMeetingRoom() {
		return meetingRoom;
	}

	/**
	 * @param meetingRoom the meetingRoom to set
	 */
	public void setMeetingRoom(String meetingRoom) {
		this.meetingRoom = meetingRoom;
	}


	/**
	 * @return the on
	 */
	public String getOn() {
		return on;
	}

	/**
	 * @param on the on to set
	 */
	public void setOn(String on) {
		this.on = on;
	}

	/**
	 * @return the roomIsBookedUnderYour
	 */
	public String getRoomIsBookedUnderYour() {
		return roomIsBookedUnderYour;
	}

	/**
	 * @param roomIsBookedUnderYour the roomIsBookedUnderYour to set
	 */
	public void setRoomIsBookedUnderYour(String roomIsBookedUnderYour) {
		this.roomIsBookedUnderYour = roomIsBookedUnderYour;
	}

	/**
	 * @return the beforeEscalationMailMinutes
	 */
	public int getBeforeEscalationMailMinutes() {
		return beforeEscalationMailMinutes;
	}

	/**
	 * @param beforeEscalationMailMinutes the beforeEscalationMailMinutes to set
	 */
	public void setBeforeEscalationMailMinutes(int beforeEscalationMailMinutes) {
		this.beforeEscalationMailMinutes = beforeEscalationMailMinutes;
	}

	/**
	 * @return the escalation
	 */
	public int getEscalation() {
		return escalation;
	}

	/**
	 * @param escalation the escalation to set
	 */
	public void setEscalation(int escalation) {
		this.escalation = escalation;
	}

	/**
	 * @return the escalationBefore
	 */
	public int getEscalationBefore() {
		return escalationBefore;
	}

	/**
	 * @param escalationBefore the escalationBefore to set
	 */
	public void setEscalationBefore(int escalationBefore) {
		this.escalationBefore = escalationBefore;
	}

	/**
	 * @return the escalationBeforeApproval
	 */
	public int getEscalationBeforeApproval() {
		return escalationBeforeApproval;
	}

	/**
	 * @param escalationBeforeApproval the escalationBeforeApproval to set
	 */
	public void setEscalationBeforeApproval(int escalationBeforeApproval) {
		this.escalationBeforeApproval = escalationBeforeApproval;
	}

	/**
	 * @return the escalationBeforeResolver
	 */
	public int getEscalationBeforeResolver() {
		return escalationBeforeResolver;
	}

	/**
	 * @param escalationBeforeResolver the escalationBeforeResolver to set
	 */
	public void setEscalationBeforeResolver(int escalationBeforeResolver) {
		this.escalationBeforeResolver = escalationBeforeResolver;
	}

	/**
	 * @return the roomBookingCode
	 */
	public String getRoomBookingCode() {
		return roomBookingCode;
	}

	/**
	 * @param roomBookingCode the roomBookingCode to set
	 */
	public void setRoomBookingCode(String roomBookingCode) {
		this.roomBookingCode = roomBookingCode;
	}

	/**
	 * @return the roomBookingName
	 */
	public String getRoomBookingName() {
		return roomBookingName;
	}

	/**
	 * @param roomBookingName the roomBookingName to set
	 */
	public void setRoomBookingName(String roomBookingName) {
		this.roomBookingName = roomBookingName;
	}

	/**
	 * @return the roomSubject
	 */
	public String getRoomSubject() {
		return roomSubject;
	}

	/**
	 * @param roomSubject the roomSubject to set
	 */
	public void setRoomSubject(String roomSubject) {
		this.roomSubject = roomSubject;
	}

	/**
	 * @return the roomBookingApproved
	 */
	public int getRoomBookingApproved() {
		return roomBookingApproved;
	}

	/**
	 * @param roomBookingApproved the roomBookingApproved to set
	 */
	public void setRoomBookingApproved(int roomBookingApproved) {
		this.roomBookingApproved = roomBookingApproved;
	}

	/**
	 * @return the roomBookingRejected
	 */
	public int getRoomBookingRejected() {
		return roomBookingRejected;
	}

	/**
	 * @param roomBookingRejected the roomBookingRejected to set
	 */
	public void setRoomBookingRejected(int roomBookingRejected) {
		this.roomBookingRejected = roomBookingRejected;
	}

	/**
	 * @return the roomBookingResubmit
	 */
	public int getRoomBookingResubmit() {
		return roomBookingResubmit;
	}

	/**
	 * @param roomBookingResubmit the roomBookingResubmit to set
	 */
	public void setRoomBookingResubmit(int roomBookingResubmit) {
		this.roomBookingResubmit = roomBookingResubmit;
	}

	/**
	 * @return the roomBookingApproval
	 */
	public int getRoomBookingApproval() {
		return roomBookingApproval;
	}

	/**
	 * @param roomBookingApproval the roomBookingApproval to set
	 */
	public void setRoomBookingApproval(int roomBookingApproval) {
		this.roomBookingApproval = roomBookingApproval;
	}

	/**
	 * @return the requestResubmit
	 */
	public String getRequestResubmit() {
		return requestResubmit;
	}

	/**
	 * @param requestResubmit the requestResubmit to set
	 */
	public void setRequestResubmit(String requestResubmit) {
		this.requestResubmit = requestResubmit;
	}
	
	
	
	
}
