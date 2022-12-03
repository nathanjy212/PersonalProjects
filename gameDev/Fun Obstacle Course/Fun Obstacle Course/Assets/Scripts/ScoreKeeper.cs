using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ScoreKeeper : MonoBehaviour
{
    int hitPoints = 0;

    private void OnCollisionEnter(Collision other) {
        if (other.gameObject.tag != "Hit") {
            hitPoints++;
            Debug.Log("You bumped into something " + hitPoints + " times");
        } 
        

    }
}
