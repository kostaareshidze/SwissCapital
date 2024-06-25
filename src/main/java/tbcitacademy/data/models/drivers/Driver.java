package tbcitacademy.data.models.drivers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Driver{

	@JsonProperty("MRData")
	private MRData mRData;

	public void setMRData(MRData mRData){
		this.mRData = mRData;
	}

	public MRData getMRData(){
		return mRData;
	}

	@Override
 	public String toString(){
		return 
			"Driver{" + 
			"mRData = '" + mRData + '\'' + 
			"}";
		}
}