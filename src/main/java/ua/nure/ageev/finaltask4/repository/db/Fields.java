package ua.nure.ageev.finaltask4.repository.db;

/**
 * Holder for fields names of DB tables and beans.
 *
 * @author A.Ageev
 */
public final class Fields {
    private Fields() {
        // not need to implement because constructor is privet to avoid attempt to instance creation
    }

    // entities
    public static final String ENTITY_ID = "id";

    public static final String USER_LOGIN = "user_name";
    public static final String USER_PASSWORD = "password";
    public static final String USER_PASSWORD_SALT = "password_salt";
    public static final String USER_PASSWORD_HASH_ALGORITHM = "password_hash_algorithm";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_ROLE_ID = "role_id";
    public static final String USER_STATUS = "user_status";

    public static final String SUBJECT_NAME = "subject_name";

    public static final String TEST_DIFFICULTY_LEVEL = "difficulty";
    public static final String TEST_MINUTES_TO_COMPLETE = "min_to_complete";
    public static final String TEST_NAME = "test_name";
    public static final String TEST_QUESTION_QUANTITY = "question_quantity";
    public static final String TEST_AVG_EVALUATION = "aver_evaluation";

    public static final String QUESTION_TEXT = "question_text";

    public static final String ANSWER_TEXT = "answer_text";
    public static final String ANSWER_IS_CORRECT = "is_correct";

    public static final String USER_RESULT_USER_ID = "user_id";
    public static final String USER_RESULT_TEST_ID = "test_id";
    public static final String USER_RESULT_EVALUATION = "evaluation";
    public static final String USER_RESULT_DATE_EVALUATION = "date_evaluation";

}