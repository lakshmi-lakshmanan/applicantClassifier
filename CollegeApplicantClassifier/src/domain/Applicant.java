package domain;

/**
 * This pojo class stores the applicant's information.
 * @author 
 *
 */
public class Applicant {

	public String firstName;
	public String lastName;
	public int age;
	public String state;
	public Double gpaScore;
	public Double maximumScore;
	public int satScore;
	public int actScore;
	public int numberOfFelony;
	public String verdict;
	public String reason;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getGpaScore() {
		return gpaScore;
	}

	public void setGpaScore(Double gpaScore) {
		this.gpaScore = gpaScore;
	}

	public Double getMaximumScore() {
		return maximumScore;
	}

	public void setMaximumScore(Double maximumScore) {
		this.maximumScore = maximumScore;
	}

	public int getSatScore() {
		return satScore;
	}

	public void setSatScore(int satScore) {
		this.satScore = satScore;
	}

	public int getActScore() {
		return actScore;
	}

	public void setActScore(int actScore) {
		this.actScore = actScore;
	}

	public int getNumberOfFelony() {
		return numberOfFelony;
	}

	public void setNumberOfFelony(int numberOfFelony) {
		this.numberOfFelony = numberOfFelony;
	}

	public String getVerdict() {
		return verdict;
	}

	public void setVerdict(String verdict) {
		this.verdict = verdict;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
