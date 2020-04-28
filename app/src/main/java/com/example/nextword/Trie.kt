package com.example.nextword

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

/**
 * Class Trie implements the Trie data structure for auto complete.
 * Words are stored in a tree like data structure.
 * Each character is represented as a TrieNode which contains a map of other TrieNode to represent subsequent characters
 * EX:
 *                  *
 *                  /
 *                  h
 *                  /\
 *                  e i
 *                  / /\
 *                  l t g
 *                  /    \
 *                  l     h
 *                  /
 *                  0
 */

class Trie {
    private val t = "Trie"

    /**
     * TrieNode represents a character in the Trie
     */
    private class TrieNode{
        var endOfWord: Boolean = false //set to "true" when the end of a word is reached
        var charMap = hashMapOf<Char, TrieNode>() //map of characters to other nodes to continue a word
        var charFreq = hashMapOf<Char, Int>() //map of character frequency to determine which character should come next in autocomplete
        var charList: ArrayList<Char> = ArrayList() //list of characters by frequency. Exits only because map can't sorted by value
    }
    private var root = TrieNode()
    //high
    //hit           <--- Example words
    //hisham
    /**
     * insertWord function to populate trie with word
     */
    fun insertWord(word: String){
        var tempNode = root
        for(c in word){
            if(!tempNode.charMap.containsKey(c)){
                tempNode.charMap[c] = TrieNode()
                tempNode.charFreq[c] = 0;
            }
            tempNode.charFreq[c] = tempNode.charFreq[c]!! + 1
            if(!tempNode.charList.contains(c))tempNode.charList.add(c)
            tempNode.charList.sortBy {tempNode.charFreq[it]}
            tempNode = tempNode.charMap[c]!!
        }
        tempNode.endOfWord = true
    }
    private var startNode = root

    /**
     * get rest of word returns top 3 most frequent words based on last character input
     */
    fun getRestOfWord(c: Char): List<String>{
        Log.d(t, "Entered getRestOfWord function...")
        Log.d(t, c.toString())
        var wordList = ArrayList<String>()
        wordList.add(String())
        wordList.add(String())
        wordList.add(String())
        var charArrays = ArrayList<ArrayList<Char>>(3)
        charArrays.add(ArrayList())
        charArrays.add(ArrayList())
        charArrays.add(ArrayList())
        charArrays[0].add(c)
        charArrays[1].add(c)
        charArrays[2].add(c)
        if(startNode.charMap[c] == null)return wordList
        startNode = startNode.charMap[c]!!
        nextWord(startNode,  1, charArrays[0])
        nextWord(startNode,  2, charArrays[1])
        nextWord(startNode,  3, charArrays[2])
        wordList[0] = charArrays[0].toString()
        wordList[1] = charArrays[1].toString()
        wordList[2] = charArrays[2].toString()

        return wordList
    }
    //hisham
    /**
     * continues the word based on the last character passed in as currentNode
     * charNumber: k-th most frequent character. ex charNumber = 2 means second most frequent character
     * word: the list to populate with the next character
     */
    private fun nextWord(currentNode: TrieNode, charNumber: Int, word: ArrayList<Char>){
        if(currentNode.charList.isEmpty())return
        var nextChar = currentNode.charList[currentNode.charList.size - 1]
        var i = 1
        while(currentNode.charList.size - 1 - i >= 0 && i < charNumber){
            nextChar = currentNode.charList[currentNode.charList.size - 1 - i]
            i++
        }
        word.add(nextChar)
        Log.d(t, nextChar.toString())
        var nextNode = currentNode.charMap[nextChar]
        if (nextNode != null) nextWord(nextNode, 1, word) // recursive call since this a tree-traversal system

    }
}