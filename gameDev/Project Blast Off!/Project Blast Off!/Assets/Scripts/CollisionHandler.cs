using UnityEngine;
using UnityEngine.SceneManagement;

public class CollisionHandler : MonoBehaviour
{
    [SerializeField] float levelLoadDelay = 2f;
    [SerializeField] AudioClip finish;
    [SerializeField] AudioClip crash;

    [SerializeField] ParticleSystem successParticles;
    [SerializeField] ParticleSystem crashParticles;

    AudioSource audioSource;

    bool isTransitioning;
    bool collisionDisable = false;


    // in order for you to make sure the SerializedField thing works
    // if the type is not a normal primitive type like int, float
    // you need to add it in the Start() and do the GetComponent thing
    void Start() {
        audioSource = GetComponent<AudioSource>();
    }

    void Update() {
        RespondToDebugKeys();
    }

    void RespondToDebugKeys() {
        if (Input.GetKeyDown(KeyCode.L)) {
            LoadNextLevel();
        } else if (Input.GetKeyDown(KeyCode.C)) {
            collisionDisable = !collisionDisable; // this will toggle collision
        }
    }

    void OnCollisionEnter(Collision other) {

        if (isTransitioning == true || collisionDisable == true) {
            return;
        }

        switch (other.gameObject.tag) {
            case "Friendly":
                Debug.Log("This thing is friendly");
                break;
            case "Finish":
                Debug.Log("Congrats!!! You finished this level!");
                StartNextLevelSequence();
                break;
            case "Fuel":
                Debug.Log("Pick up some fuel");
                break;
            default:
                Debug.Log("CRASH!!! Retry!");
                StartCrashSequence();
                // ReloadLevel();
                break;
        }    
    }

    void StartNextLevelSequence() {
        isTransitioning = true;
        audioSource.Stop();
        audioSource.PlayOneShot(finish);
        // to add particle effects on success
        successParticles.Play();
        GetComponent<Movement>().enabled = false;
        Invoke("LoadNextLevel", levelLoadDelay); 
    }

    void StartCrashSequence() {
        isTransitioning = true;
        audioSource.Stop();
        audioSource.PlayOneShot(crash);
        crashParticles.Play();
        // need to remove the ability to 
        GetComponent<Movement>().enabled = false;
        Invoke("ReloadLevel", levelLoadDelay);

    }

    void LoadNextLevel() {
        int currentScene = SceneManager.GetActiveScene().buildIndex;
        int nextScene = currentScene + 1;
        if (nextScene == SceneManager.sceneCountInBuildSettings) {
            nextScene = 0;
        }
        SceneManager.LoadScene(nextScene);
    }

    void ReloadLevel() {
        int currentScene = SceneManager.GetActiveScene().buildIndex;
        SceneManager.LoadScene(currentScene);
    }
}
