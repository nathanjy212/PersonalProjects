using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Mover : MonoBehaviour
{
    // [SerializeField] float xValue = -0.01f;
    // [SerializeField] float yValue = 0.01f;
    // [SerializeField] float zValue = 0f;

    // for things that don't need to constantly update, put it here.
    // dont put it in the Start because that's for all the initial placements of the game
    [SerializeField] float moveSpeed = 12f;

    // Start is called before the first frame update
    void Start() {
        PrintInstructions();
        
    }

    // Update is called once per frame
    void Update() {
        PlayerMovement();
        
    }

    void PrintInstructions() {
        Debug.Log("Welcome to THE obstacle course!");
        Debug.Log("Move Toby using the arrow keys!"); 
        Debug.Log("Try reaching the end without hitting any walls!!");
    }

    void PlayerMovement() {
        // we use Time.deltaTime so that the framerate speed is independent of your graphics card frame rate
        float xValue = Input.GetAxis("Horizontal") * Time.deltaTime * moveSpeed;
        float zValue = Input.GetAxis("Vertical") * Time.deltaTime * moveSpeed;


        transform.Translate(xValue, 0.0f, zValue);
    }



}
