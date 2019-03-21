package classifier;

import domain.Applicant;
import domain.Constants;

public class ApplicantClassifierHelper {

	/**
	 * This method converts gpa to percentage score;
	 * 
	 * @param gpaScore
	 * @param maximumScore
	 */
	protected Double calculatePercentageGpa(Double gpaScore, Double maximumScore) {

		Double percentageScore = null;
		if (maximumScore != null && maximumScore > 0) {
			percentageScore = (gpaScore / maximumScore) * 100;
		}
		return percentageScore;
	}

	/**
	 * Checks for first name and last name conditions. First Character to be upper
	 * case and rest lower case.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	protected Boolean checkPatternforNames(Applicant applicant) {
		Boolean instantReject = Boolean.FALSE;
		char[] charsFirstName = null;
		char[] charsLastName = null;
		if (applicant.getFirstName() != null)
			charsFirstName = applicant.getFirstName().toCharArray();
		if (applicant.getLastName() != null)
			charsLastName = applicant.getLastName().toCharArray();

		instantReject = checkFirstLetterCase(instantReject, applicant, charsFirstName,
				Constants.INSTANT_REJECT_FIRST_NAME_UPPER_CASE);

		instantReject = checkFirstLetterCase(instantReject, applicant, charsLastName,
				Constants.INSTANT_REJECT_LAST_NAME_UPPER_CASE);

		instantReject = checkRemainingLettersCase(instantReject, charsFirstName, applicant,
				Constants.INSTANT_REJECT_FIRST_NAME_LOWER);

		instantReject = checkRemainingLettersCase(instantReject, charsLastName, applicant,
				Constants.INSTANT_REJECT_LAST_NAME_LOWER);

		return instantReject;
	}

	/**
	 * Checks for lower case of the chars from index 1
	 * 
	 * @param instantReject
	 * @param nameChars
	 * @param applicant
	 * @param reason
	 * @return Boolean
	 */
	private Boolean checkRemainingLettersCase(Boolean instantReject, char[] nameChars, Applicant applicant,
			String reason) {
		if (instantReject != Boolean.TRUE && nameChars != null) {
			for (int i = 1; i < nameChars.length; i++) {
				if (!Character.isLowerCase(nameChars[i])) {
					instantReject = Boolean.TRUE;
					applicant.setReason(reason + (i + 1));
					break;
				}
			}
		}
		return instantReject;
	}

	/**
	 * Checks for upper case for first index.
	 * 
	 * @param applicant
	 * @param nameChar
	 * @param reason
	 * @return Boolean
	 */
	private Boolean checkFirstLetterCase(Boolean instantReject, Applicant applicant, char[] nameChar, String reason) {
		if (nameChar != null && !(Character.isUpperCase(nameChar[Constants.INDEX_FOR_FIRST_NAME_REJECT]))) {
			instantReject = Boolean.TRUE;
			applicant.setReason(reason);
		}
		return instantReject;
	}
}
