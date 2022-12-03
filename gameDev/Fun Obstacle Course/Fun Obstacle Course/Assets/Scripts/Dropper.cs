using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Dropper : MonoBehaviour
{
    MeshRenderer renderer;
    Rigidbody rigidbody;
    [SerializeField] float timeToWait = 3f;

    // Start is called before the first frame update
    void Start()
    {
        rigidbody = GetComponent<Rigidbody>();
        rigidbody.useGravity = false;
        renderer = GetComponent<MeshRenderer>();
        renderer.enabled = false;
    
    }

    // Update is called once per frame
    void Update()
    {
        if (Time.time > timeToWait) {
            rigidbody.useGravity = true;
            renderer.enabled = true;
            Debug.Log("3 seconds have past");
        }
        
    }
}
