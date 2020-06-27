package com.lzq.programProblem.primary;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author laizhiqiang
 * @Description:初级编程题，同字母异序词,
 * 当两个或两个以上的单词由相同的字符以不同的顺序组成时，它们就被称为同字母异序词。
 *
 * 任务
 * 使用http://wiki.puzzlers.org/pub/wordlists/unixdict.txt上的单词列表，
 * 查找包含最多单词的相同字符的单词集。
 *
 * 参考如下：
 *
 * [angel, angle, galen, glean, lange]
 * [elan, lane, lean, lena, neal]
 * [alger, glare, lager, large, regal]
 * [abel, able, bale, bela, elba]
 * [evil, levi, live, veil, vile]
 * [caret, carte, cater, crate, trace]
 *
 * @Date 2020/6/15 0015 16:23
 */
public class Anagrams {
    public static void main(String...args) throws Exception {
        URL url=new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
        String word;
        Map<String, Collection> anagrams=new HashMap<>();
        int count=0;
        while ((word=br.readLine())!=null){
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            if (!anagrams.containsKey(s)){
                anagrams.put(s,new ArrayList());
            }
            anagrams.get(s).add(word);
            count=Math.max(count,anagrams.get(s).size());
        }
        int finalCount = count;
        anagrams.forEach((x, y)->{
            if (finalCount <=y.size())
            System.out.println(y);
        });
        br.close();
    }
}
