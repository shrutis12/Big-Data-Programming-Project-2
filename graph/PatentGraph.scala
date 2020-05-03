import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx._
import org.graphframes.GraphFrame

val patentGraph = GraphLoader.edgeListFile(sc, "cit-Patents.txt")
val graphFrame = GraphFrame.fromGraphX(patentGraph)
val pattern = "(x1) - [a] -> (x2); (x2) - [b] -> (x3)"
val paths = graphFrame.find(pattern)
paths.select($"x1.attr", $"x3.attr", $"a.attr" + $"b.attr").show(10)