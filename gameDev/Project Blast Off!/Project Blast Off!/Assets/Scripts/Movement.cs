using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movement : MonoBehaviour
{
    // PARAMETERs for tuning, typically set in the editor

    // CACHE eg. references for readibility or speed

    // STATE - private instance (member) variables
    
    [SerializeField] float mainThrust = 100f;
    [SerializeField] float rotationThrust = 100f;
    [SerializeField] AudioClip mainEngine;

    [SerializeField] ParticleSystem mainEngineParticles;
    [SerializeField] ParticleSystem leftThrusterParticles;
    [SerializeField] ParticleSystem rightThrusterParticles;

    Rigidbody rb;
    AudioSource audioSource;
    

    // state is something like bool isAlive;


    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody>();
        audioSource = GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        ProcessThrust();
        ProcessRotation();
    }

    void ProcessThrust() {
        // this is for the space jump button
        if (Input.GetKey(KeyCode.Space) == true) {
            // vector 3 can help keep track of 3 things when rocket is moving
            rb.AddRelativeForce(Vector3.up * mainThrust * Time.deltaTime); // this can also be Vector3.up // this Time.deltaTime makes the graphics card thing indpenedent
            if (!audioSource.isPlaying) {
                audioSource.PlayOneShot(mainEngine);
            }
            // play the main booster particles here
            if (!mainEngineParticles.isPlaying) {
                mainEngineParticles.Play();
            }
            
            
        } else {
            audioSource.Stop();
            mainEngineParticles.Stop();
        }
    }

    void ProcessRotation() {
        // this is for the left button and the right button
        if (Input.GetKey(KeyCode.A) == true) {
            ApplyRotation(rotationThrust);
            if (!rightThrusterParticles.isPlaying) {
                rightThrusterParticles.Play();
            }

        } else if (Input.GetKey(KeyCode.D) == true) {
            ApplyRotation(-rotationThrust);
            if (!leftThrusterParticles.isPlaying) {
                leftThrusterParticles.Play();
            }
        } else {
            rightThrusterParticles.Stop();
            leftThrusterParticles.Stop();
        }
    }

    void ApplyRotation(float rotationThisFrame) {

        rb.freezeRotation = true; // freezing rotation so we can manually rotate
        transform.Rotate(Vector3.forward * rotationThisFrame * Time.deltaTime);
        rb.freezeRotation = false; // unfreeze rotation so that the physics system can take over
    }

}
