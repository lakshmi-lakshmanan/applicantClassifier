package domain;

/**
 * 
 * This class contains the review outcome types.
 *
 */
public enum ReviewOutcomeType {

	INSTANT_ACCEPT("Instanct Accept"), INSTANT_REJECT("Instanct Reject"), FURTHER_REVIEW("Further Review");

	public String verdict;

	ReviewOutcomeType(String verdict) {
		this.verdict = verdict;
	}
}
