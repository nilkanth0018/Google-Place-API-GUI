package com.demo.googleplacesearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "business_status", "geometry", "icon", "opening_hours", "photos", "plus_code", "rating",
		"reference", "types", "user_ratings_total" , "price_level", "permanently_closed" })
public class SearchEntity {

	@JsonProperty("formatted_address")
	private String formattedAddress;

	@JsonProperty("name")
	private String name;

	@JsonProperty("place_id")
	private String placeId;

	@JsonIgnore
	private String businessAddress;

	@JsonIgnore
	private String city;

	@JsonIgnore
	private String state;

	@JsonIgnore
	private String zipCode;

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
		String[] splitAddress = this.formattedAddress.split(",");
		if (splitAddress.length >= 4) {
			businessAddress = "";
			for (int i = 0; i < splitAddress.length - 3; i++) {
				businessAddress = businessAddress + ", " + splitAddress[i].trim();
			}
			businessAddress = businessAddress.replaceFirst(", ", "");

			city = splitAddress[splitAddress.length - 3].trim();
			String[] stateZipStr = splitAddress[splitAddress.length - 2].trim().split(" ");
			if (stateZipStr.length >= 2) {
				state = stateZipStr[0].trim();
				zipCode = stateZipStr[1].trim();
			}
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
