package com.donte.placasocr.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;

public class PlatesOcr{
			
	private transient Long id;
	private transient Long eventId;
	private transient byte[] image;
	private transient boolean sent;	
	private transient Calendar timeShot;
	private String uuid;
	private String typeReader;
	private String device;
	private String plate;
	private String encodedImage;	
	
	public PlatesOcr(){}
		
	public PlatesOcr(ResultSet rs) throws SQLException {
		id = rs.getLong("id");
		eventId = rs.getLong("eventid");
		image = rs.getBytes("image");
		sent = rs.getBoolean("sent");
		timeShot = timestampToCalendar(rs.getTimestamp("timeshot"));
		
		uuid = UUID.randomUUID().toString();
		typeReader = rs.getString("typereader");
		device = rs.getString("device");
		plate = rs.getString("plate");
		encodedImage = Base64.encodeBase64String(image);				
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Calendar getTimeShot() {
		return timeShot;
	}

	public void setTimeShot(Calendar timeShot) {
		this.timeShot = timeShot;
	}

	public String getTypeReader() {
		return typeReader;
	}

	public void setTypeReader(String typeReader) {
		this.typeReader = typeReader;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}
	
	public String getEncodedImage() {		
		return encodedImage;
	}

	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	private Calendar timestampToCalendar(Timestamp date) {		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlatesOcr other = (PlatesOcr) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String toJson(){
		return new Gson().toJson(this);
	}
	
	public String toString(){
		String dataHora = Propriedades.getDataFormatada(timeShot.getTime());
		return "OCR [ID = "+id+", EVENTID = "+ eventId+ ", PLACA = "+plate+", DEVICE = "+device+", TIMESHOT = "+dataHora+"]";
	}

}
