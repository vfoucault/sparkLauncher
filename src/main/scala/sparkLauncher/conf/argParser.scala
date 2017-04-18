package sparkLauncher.conf


/**
  * Created by vfoucault on 18/04/2017.
  */

import java.io.File

case class SparkLauncherConfig(appName: String = "Spark application",
                               jarFile: String,
                               klass: String,
                               sparkHome: String,
                               executorMem: Int = 5,
                               executorCpu: Int = 2,
                               master: String = "yarn",
                               deployMode: String = "cluster",
                               driverMemory: Int = 4,
                               queue: String = "default",
                               optArgs: Seq[String] = Seq(),
                               kwargs: Map[String, String] = Map()
                              )

class Parser {
  val parser = new scopt.OptionParser[SparkLauncherConfig]("SparkLauncher") {
    head("spark launcher")

    opt[String]('n', "name").action((x,c) =>
      c.copy(appName = x)).text("Spark Application Name")

     opt[String]('j', "jar").action((x,c) =>
      c.copy(jarFile = x)).text("target jar file")

    opt[String]('k', "klass").action((x,c) =>
      c.copy(klass = x)).text("target class name")

    opt[String]('h', "spark-home").action((x,c) =>
      c.copy(sparkHome = x)).text("sparkhome")

    opt[Int]('m', "executor-mem").action((x,c) =>
      c.copy(executorMem = x)).text("Executor Memory")

    opt[Int]('c', "executor-core").action((x,c) =>
      c.copy(executorCpu = x)).text("Executor Core")

    opt[Int]('d', "driver-memory").action((x,c) =>
      c.copy(driverMemory = x)).text("Driver Memory")

    opt[String]('a', "master").action((x,c) =>
      c.copy(master = x)).text("maser (local, yarn...)")

    opt[String]('e', "deploy-mode").action((x,c) =>
      c.copy(deployMode = x)).text("deploy mode")

    opt[String]('q', "queue").action((x,c) =>
      c.copy(deployMode = x)).text("yarn queue")

    opt[String]('e', "deploy-mode").action((x,c) =>
      c.copy(deployMode = x)).text("deploy mode")

    opt[Seq[String]]("arguments").valueName("<arg1>,<arg2>...").action((x, c) =>
      c.copy(optArgs = x)).text("arguments to pass")

    opt[Map[String, String]]("kwargs").valueName("k1=v1,k2=v2...").action((x, c) =>
      c.copy(kwargs = x)).text("keywords arguments")



  }
  // parser.parse returns Option[C]
  parser.parse(args, Config()) match {
    case Some(config) =>
    // do stuff

    case None =>
    // arguments are bad, error message will have been displayed
  }
}

/*
case class Config(foo: Int = -1, out: File = new File("."), xyz: Boolean = false,
                  libName: String = "", maxCount: Int = -1, verbose: Boolean = false, debug: Boolean = false,
                  mode: String = "", files: Seq[File] = Seq(), keepalive: Boolean = false,
                  jars: Seq[File] = Seq(), kwargs: Map[String, String] = Map())

class parser {
  val parser = new scopt.OptionParser[Config]("scopt") {
    head("scopt", "3.x")

    opt[Int]('f', "foo").action((x, c) =>
      c.copy(foo = x)).text("foo is an integer property")

    opt[File]('o', "out").required().valueName("<file>").
      action((x, c) => c.copy(out = x)).
      text("out is a required file property")

    opt[(String, Int)]("max").action({
      case ((k, v), c) => c.copy(libName = k, maxCount = v)
    }).
      validate(x =>
        if (x._2 > 0) success
        else failure("Value <max> must be >0")).
      keyValueName("<libname>", "<max>").
      text("maximum count for <libname>")

    opt[Seq[File]]('j', "jars").valueName("<jar1>,<jar2>...").action((x, c) =>
      c.copy(jars = x)).text("jars to include")

    opt[Map[String, String]]("kwargs").valueName("k1=v1,k2=v2...").action((x, c) =>
      c.copy(kwargs = x)).text("other arguments")

    opt[Unit]("verbose").action((_, c) =>
      c.copy(verbose = true)).text("verbose is a flag")

    opt[Unit]("debug").hidden().action((_, c) =>
      c.copy(debug = true)).text("this option is hidden in the usage text")

    help("help").text("prints this usage text")

    arg[File]("<file>...").unbounded().optional().action((x, c) =>
      c.copy(files = c.files :+ x)).text("optional unbounded args")

    note("some notes.".newline)

    cmd("update").action((_, c) => c.copy(mode = "update")).
      text("update is a command.").
      children(
        opt[Unit]("not-keepalive").abbr("nk").action((_, c) =>
          c.copy(keepalive = false)).text("disable keepalive"),
        opt[Boolean]("xyz").action((x, c) =>
          c.copy(xyz = x)).text("xyz is a boolean property"),
        opt[Unit]("debug-update").hidden().action((_, c) =>
          c.copy(debug = true)).text("this option is hidden in the usage text"),
        checkConfig(c =>
          if (c.keepalive && c.xyz) failure("xyz cannot keep alive")
          else success)
      )
  }

  // parser.parse returns Option[C]
  parser.parse(args, Config()) match {
    case Some(config) =>
    // do stuff

    case None =>
    // arguments are bad, error message will have been displayed
  }

}
*/