{
  pkgs,
  lib,
  config,
  inputs,
  ...
}:

{
  packages = with pkgs;[ curl unzip ];

  languages.java = {
    enable = true;
    jdk.package = pkgs.jdk21;
  };
}
