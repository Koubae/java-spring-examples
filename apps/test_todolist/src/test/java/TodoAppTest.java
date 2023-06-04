import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.text.MessageFormat;

import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.svenjacobs.loremipsum.LoremIpsum;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runners.MethodSorters;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import com.koubae.model.Task;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TodoAppTest {
    private static final boolean DEBUG = false;
    private static final String BASE_URI = "http://localhost:8080";
    private static final int PORT = 8080;
    private static final String BASE_API_URI = "/api/v1";
    private static final String TASK_UI = "/tasks";

    private static final String API_GET_BY_ID = String.format("%s/{0}", TASK_UI);
    private static final String API_GET_BY_NAME = String.format("%s/{0}/", TASK_UI);
    private static final String API_GET_TODO = String.format("%s/todo", TASK_UI);
    private static final String API_GET_COMPLETE = String.format("%s/complete", TASK_UI);
    private static final String API_MARK_TODO = String.format("%s/{0}/todo", TASK_UI);
    private static final String API_MARK_COMPLETE = String.format("%s/{0}/complete", TASK_UI);

    private static final String TASK_BASE_NAME = "task_integration_test";

    private static final LoremIpsum loremIpsum = new LoremIpsum();

    private static String taskName = "";
    private static List<Task> tasks = new ArrayList<>();

    @BeforeAll
    static void init() {
        TodoAppTest.taskName = createRandomTaskName();
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = PORT;
        RestAssured.basePath = BASE_API_URI;
    }

    @Test
    @DisplayName("Create 1 com.koubae.model.Task")
    void test_1() {
        Task task = createRandomTask();
        given().contentType(ContentType.JSON)
                .body(task.toJSONCreate().toString())
                .when().post(TASK_UI).then().statusCode(201);
    }

    @Test
    @DisplayName("Create Many Tasks 1")
    void test_2() {
        int totalTasks = ThreadLocalRandom.current().nextInt(1, 5);

        for (int i = 0; i < totalTasks; i++) {
            Response response = given().contentType(ContentType.JSON)
                    .body(createRandomTask().toJSONCreate().toString()).when().post(TASK_UI).then().extract().response();
            JsonPath body = response.jsonPath();

            long taskId =  body.getLong("id");
            String taskName = body.getString("name");
            String description = body.getString("description");
            boolean completed = body.getBoolean("completed");
            String created = body.getString("created");
            String updated = body.getString("updated");

            Task task = new Task(taskId, taskName, description, completed, created, updated);
            tasks.add(task);
        }

    }

    @Test
    @DisplayName("Mark Task Completed")
    void tes_mark_completed() {
        markTest(true);
    }

    @Test
    @DisplayName("Mark Task Todo")
    void tes_mark_todo() {
        markTest(false);
    }

    @Test
    @DisplayName("Get Task by Name")
    void test_3() {
        String description = loremIpsum.getWords(ThreadLocalRandom.current().nextInt(10, 150 + 1));

        Task task = new Task(TodoAppTest.taskName, description);
        given().contentType("application/json")
                .body(task.toJSONCreate().toString())
                .when().post(TASK_UI).then().statusCode(201);

        Response response = given().contentType(ContentType.JSON)
                .when().get(MessageFormat.format(API_GET_BY_NAME, TodoAppTest.taskName)).then().extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(TodoAppTest.taskName, response.jsonPath().getString("name"));
    }

    @Test
    @DisplayName("Get Task By Id")
    void test_4() {
        Task task = createRandomTask();
        Response response = given().contentType(ContentType.JSON).body(task.toJSONCreate().toString()).post(TASK_UI).then().extract().response();

        int taskId =  response.jsonPath().getInt("id");
        when().get(MessageFormat.format(API_GET_BY_ID, taskId)).then().statusCode(200).and().assertThat().body("id", Matchers.equalTo(taskId));
    }

    @Test
    @DisplayName("Get and print Tasks")
    void test_5() {
        Response tasks = get(TASK_UI);
        if (DEBUG) {
            tasks.getBody().print();
        }
    }

    @Test
    @DisplayName("Get All Tasks does not return an empty list since we've created them before")
    void test_6() {
        when().get(TASK_UI).then().statusCode(200).and().assertThat().body("", Matchers.not(Matchers.empty()));
    }

    @Test
    @DisplayName("Delete Tasks")
    void test_delete() {
        int totalTasks = ThreadLocalRandom.current().nextInt(1, 5);

        for (int i = 0; i < totalTasks; i++) {
            Response response = given().contentType(ContentType.JSON)
                    .body(createRandomTask().toJSONCreate().toString()).when().post(TASK_UI).then().extract().response();
            JsonPath body = response.jsonPath();

            long taskId =  body.getLong("id");
            String taskName = body.getString("name");
            String description = body.getString("description");
            boolean completed = body.getBoolean("completed");
            String created = body.getString("created");
            String updated = body.getString("updated");

            Task task = new Task(taskId, taskName, description, completed, created, updated);
            tasks.add(task);
        }

        for (Task task : tasks) {
            Long taskId = task.getId();
            when().delete(MessageFormat.format(API_GET_BY_ID, taskId)).then().statusCode(200);
        }

    }

    static String createRandomTaskName() {
        return TASK_BASE_NAME + UUID.randomUUID();
    }

    Task createRandomTask() {
        String description = loremIpsum.getWords(ThreadLocalRandom.current().nextInt(10, 150 + 1));
        return new Task(createRandomTaskName(), description);
    }

    /**
     * Marks a random set of newly created test either as 'completed' or 'todo' Then checks if
     * The marked one are within the list
     * @param done {boolean}
     */
    void markTest(boolean done) {
        int totalTasks = ThreadLocalRandom.current().nextInt(1, 5);
        String endpointGet = done ? API_GET_COMPLETE : API_GET_TODO;
        String endpointMark = done ? API_MARK_COMPLETE : API_MARK_TODO;

        List<Task> tasks = createRandomSetOfTask(totalTasks);
        markTasks(endpointMark, tasks);

        Response responseList = given().contentType(ContentType.JSON).get(endpointGet).then().extract().response();
        JsonPath body = responseList.jsonPath();

        long[] taskIdArray = new long[totalTasks];
        for (int i = 0; i < totalTasks; i++) {
            Task task = tasks.get(i);
            taskIdArray[i] = task.getId();
        }

        boolean[] taskIdFound = findIdsFromTaskResponse(totalTasks, body, taskIdArray);

        Assertions.assertEquals(taskIdFound.length, taskIdArray.length);
        for (boolean value : taskIdFound) {
            Assertions.assertTrue(value);
        }
    }

    private static boolean[] findIdsFromTaskResponse(int totalTasks, JsonPath body, long[] taskIdArray) {
        JSONArray tasksResult;
        boolean[] taskIdFound = new boolean[totalTasks];

        try {
            tasksResult = new JSONArray(body.prettify());
        } catch (JSONException error) {
            System.err.println(error.getMessage());
            return taskIdFound;
        }

        int taskIdFoundCurrentId = 0;
        for (int i = 0; i < tasksResult.length(); i ++) {
            try {
                JSONObject item = tasksResult.getJSONObject(i);

                long taskId =  item.getLong("id");
                boolean isTaskIdInsideTaskIds = ArrayUtils.contains(taskIdArray, taskId);
                if (isTaskIdInsideTaskIds) {
                    taskIdFound[taskIdFoundCurrentId] = true;
                    taskIdFoundCurrentId ++;
                }
            } catch (JSONException error) {
                System.err.println(error.getMessage());
            }
        }

        return taskIdFound;

    }

    private static void markTasks(String endpointMark, List<Task> tasks) {
        tasks.forEach(task -> {
            when().put(MessageFormat.format(endpointMark, task.getId())).then().statusCode(200).and();
        });
    }

    private List<Task> createRandomSetOfTask(int totalTasks) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < totalTasks; i++) {
            Response response = given().contentType(ContentType.JSON)
                    .body(createRandomTask().toJSONCreate().toString()).when().post(TASK_UI).then().extract().response();
            JsonPath body = response.jsonPath();

            long taskId =  body.getLong("id");
            String taskName = body.getString("name");
            String description = body.getString("description");
            boolean completed = body.getBoolean("completed");
            String created = body.getString("created");
            String updated = body.getString("updated");

            Task task = new Task(taskId, taskName, description, completed, created, updated);
            tasks.add(task);
        }
        return tasks;
    }


}
