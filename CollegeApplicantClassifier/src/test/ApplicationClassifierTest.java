package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classifier.ApplicantClassifier;
import domain.Applicant;
import domain.Constants;
import domain.ReviewOutcomeType;

/**
 * This class is the JUnit test class for Applicant Classifier which determines
 * review process of a candidate.
 * 
 * @author
 *
 */
public class ApplicationClassifierTest {

	public ApplicantClassifier applicantClassifier;

	@Before
	public void before() {
		applicantClassifier = new ApplicantClassifier();
	}

	@After
	public void after() {
		applicantClassifier = null;
	}

	/**
	 * Success case for instant accept.
	 */
	@Test
	public void testApplicationClassfierInstantAccept1() {
		Applicant applicant = new Applicant();
		applicant.setAge(22);
		applicant.setGpaScore(5.0);
		applicant.setMaximumScore(5.0);
		applicant.setSatScore(1921);
		applicant.setState("California");

		Applicant responseApp = applicantClassifier.getApplicantReview(applicant);
		assertEquals(responseApp.getVerdict(), ReviewOutcomeType.INSTANT_ACCEPT.verdict);
		assertEquals(responseApp.getReason(), null);
	}

	/**
	 * Success case for instant accept with age > 80.
	 */
	@Test
	public void testApplicationClassfierInstantAccept2() {
		Applicant applicant = new Applicant();
		applicant.setAge(81);
		applicant.setGpaScore(5.0);
		applicant.setMaximumScore(5.0);
		applicant.setSatScore(1921);
		// applicant.setState("California");

		Applicant responseApp = applicantClassifier.getApplicantReview(applicant);
		assertEquals(responseApp.getVerdict(), ReviewOutcomeType.INSTANT_ACCEPT.verdict);
		assertEquals(responseApp.getReason(), null);
	}

	/**
	 * Success case for instant accept with ACT > 27.
	 */
	@Test
	public void testApplicationClassfierInstantAccept3() {
		Applicant applicant = new Applicant();
		applicant.setAge(81);
		applicant.setGpaScore(5.0);
		applicant.setMaximumScore(5.0);
		applicant.setSatScore(1911);
		applicant.setActScore(28);
		// applicant.setState("California");

		Applicant responseApp = applicantClassifier.getApplicantReview(applicant);
		assertEquals(responseApp.getVerdict(), ReviewOutcomeType.INSTANT_ACCEPT.verdict);
		assertEquals(responseApp.getReason(), null);
	}

	/**
	 * Success case for Further Review.
	 */
	@Test
	public void testApplicationClassfierFurtherReview() {
		Applicant applicant = new Applicant();
		applicant.setAge(22);
		applicant.setGpaScore(5.0);
		applicant.setMaximumScore(5.0);
		applicant.setSatScore(1921);

		Applicant responseApp = applicantClassifier.getApplicantReview(applicant);
		assertEquals(responseApp.getVerdict(), ReviewOutcomeType.FURTHER_REVIEW.verdict);
		assertEquals(responseApp.getReason(), null);
	}

	/**
	 * Test to check for Instant Reject in case of Felony.
	 */
	@Test
	public void testApplicationClassfierInstantReject1() {
		Applicant applicant = new Applicant();
		applicant.setNumberOfFelony(1);
		Applicant responseApp = applicantClassifier.getApplicantReview(applicant);
		assertEquals(responseApp.getVerdict(), ReviewOutcomeType.INSTANT_REJECT.verdict);
		assertEquals(responseApp.getReason(), Constants.INSTANT_REJECT_FELONY_REASON);
	}

	/**
	 * Test to check instant reject for gpa < 70 %
	 */
	@Test
	public void testApplicationClassfierInstantReject2() {
		Applicant applicant = new Applicant();
		applicant.setGpaScore(2.3);
		applicant.setMaximumScore(5.0);
		Applicant responseApp = applicantClassifier.getApplicantReview(applicant);
		assertEquals(responseApp.getVerdict(), ReviewOutcomeType.INSTANT_REJECT.verdict);
		assertEquals(responseApp.getReason(), Constants.INSTANT_REJECT_GPA_LESS);
	}

	/**
	 * Test to check instant reject for age in negative
	 */
	@Test
	public void testApplicationClassfierInstantReject3() {
		Applicant applicant = new Applicant();
		applicant.setAge(-1);
		Applicant responseApp = applicantClassifier.getApplicantReview(applicant);
		assertEquals(responseApp.getVerdict(), ReviewOutcomeType.INSTANT_REJECT.verdict);
		assertEquals(responseApp.getReason(), Constants.INSTANT_REJECT_AGE_INVALID);
	}

	/**
	 * Test to check instant reject for first character in first name not upper
	 * case.
	 */
	@Test
	public void testApplicationClassfierInstantReject4() {
		Applicant applicant = new Applicant();
		applicant.setAge(2);
		applicant.setFirstName("john");
		Applicant responseApp = applicantClassifier.getApplicantReview(applicant);
		assertEquals(responseApp.getVerdict(), ReviewOutcomeType.INSTANT_REJECT.verdict);
		assertEquals(responseApp.getReason(), Constants.INSTANT_REJECT_FIRST_NAME_UPPER_CASE);
	}

	/**
	 * Test to check instant reject for first character in last name not upper case.
	 */
	@Test
	public void testApplicationClassfierInstantReject5() {
		Applicant applicant = new Applicant();
		applicant.setAge(2);
		applicant.setLastName("mathew");
		Applicant responseApp = applicantClassifier.getApplicantReview(applicant);
		assertEquals(responseApp.getVerdict(), ReviewOutcomeType.INSTANT_REJECT.verdict);
		assertEquals(responseApp.getReason(), Constants.INSTANT_REJECT_LAST_NAME_UPPER_CASE);
	}

	/**
	 * Test to check instant reject for character in first name not lower case.
	 */
	@Test
	public void testApplicationClassfierInstantReject6() {
		Applicant applicant = new Applicant();
		applicant.setAge(2);
		applicant.setFirstName("JOhn");
		Applicant responseApp = applicantClassifier.getApplicantReview(applicant);
		assertEquals(responseApp.getVerdict(), ReviewOutcomeType.INSTANT_REJECT.verdict);
		assertEquals(responseApp.getReason(), Constants.INSTANT_REJECT_FIRST_NAME_LOWER);
	}

	/**
	 * Test to check instant reject for character in last name not lower case.
	 */
	@Test
	public void testApplicationClassfierInstantReject7() {
		Applicant applicant = new Applicant();
		applicant.setAge(2);
		applicant.setLastName("MatHew");
		Applicant responseApp = applicantClassifier.getApplicantReview(applicant);
		assertEquals(responseApp.getVerdict(), ReviewOutcomeType.INSTANT_REJECT.verdict);
		assertEquals(responseApp.getReason(), Constants.INSTANT_REJECT_LAST_NAME_LOWER);
	}

}
