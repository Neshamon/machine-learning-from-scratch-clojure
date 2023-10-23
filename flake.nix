{
  description = "A very basic flake";

  inputs.nixpkgs.url = "github:NixOs/nixpkgs/nixpkgs-unstable";

  outputs = { self, nixpkgs }:
    let
      javaVersion = 21;
      overlays = [
        (final: prev: rec {
          jdk = prev."jdk${toString javaVersion}";
          boot = prev.boot.override { inherit jdk; };
          clojure = prev.clojure.override { inherit jdk; };
          leiningen = prev.leiningen.override { inherit jdk; };
        })
      ];
      supportedSystems = [ "x86_64-linux" "aarch64-linux" "x86_64-darwin" "aarch64-darwin" ];
      forEachSupportedSystem = f: nixpkgs.lib.genAttrs supportedSystems (system: f {
        pkgs = import nixpkgs { inherit overlays system; };
      });
    in
      {
        devShells = forEachSupportedSystem ({ pkgs }: {
          default = pkgs.mkShell {
            packages = with pkgs; [
              boot
              clojure
              leiningen
              unzip
            ];

            shellHook = ''
              echo "Welcome to the Kaggle-Clojure env!"
            '';
          };
        });
      };
}
