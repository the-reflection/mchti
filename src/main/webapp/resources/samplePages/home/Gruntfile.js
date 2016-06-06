// AdminLTE Gruntfile
module.exports = function (grunt) {

  'use strict';

  grunt.initConfig({
    watch: {
      // If any .less file changes in directory "resources/build/less/" run the "less"-task.
      files: ["resources/build/less/*.less", "resources/build/less/skins/*.less", "resources/plugins/admin-lte-2.3.3/js/app.js"],
      tasks: ["less", "uglify"]
    },
    // "less"-task configuration
    // This task will compile all less files upon saving to create both AdminLTE.css and AdminLTE.min.css
    less: {
      // Development not compressed
      development: {
        options: {
          // Whether to compress or not
          compress: false
        },
        files: {
          // compilation.css  :  source.less
          "resources/plugins/admin-lte-2.3.3/css/AdminLTE.css": "resources/build/less/AdminLTE.less",
          //Non minified skin files
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-blue.css": "resources/build/less/skins/skin-blue.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-black.css": "resources/build/less/skins/skin-black.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-yellow.css": "resources/build/less/skins/skin-yellow.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-green.css": "resources/build/less/skins/skin-green.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-red.css": "resources/build/less/skins/skin-red.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-purple.css": "resources/build/less/skins/skin-purple.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-blue-light.css": "resources/build/less/skins/skin-blue-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-black-light.css": "resources/build/less/skins/skin-black-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-yellow-light.css": "resources/build/less/skins/skin-yellow-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-green-light.css": "resources/build/less/skins/skin-green-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-red-light.css": "resources/build/less/skins/skin-red-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-purple-light.css": "resources/build/less/skins/skin-purple-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/_all-skins.css": "resources/build/less/skins/_all-skins.less"
        }
      },
      // Production compresses version
      production: {
        options: {
          // Whether to compress or not
          compress: true
        },
        files: {
          // compilation.css  :  source.less
          "resources/plugins/admin-lte-2.3.3/css/AdminLTE.min.css": "resources/build/less/AdminLTE.less",
          // Skins minified
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-blue.min.css": "resources/build/less/skins/skin-blue.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-black.min.css": "resources/build/less/skins/skin-black.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-yellow.min.css": "resources/build/less/skins/skin-yellow.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-green.min.css": "resources/build/less/skins/skin-green.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-red.min.css": "resources/build/less/skins/skin-red.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-purple.min.css": "resources/build/less/skins/skin-purple.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-blue-light.min.css": "resources/build/less/skins/skin-blue-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-black-light.min.css": "resources/build/less/skins/skin-black-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-yellow-light.min.css": "resources/build/less/skins/skin-yellow-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-green-light.min.css": "resources/build/less/skins/skin-green-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-red-light.min.css": "resources/build/less/skins/skin-red-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/skin-purple-light.min.css": "resources/build/less/skins/skin-purple-light.less",
          "resources/plugins/admin-lte-2.3.3/css/skins/_all-skins.min.css": "resources/build/less/skins/_all-skins.less"
        }
      }
    },
    // Uglify task info. Compress the js files.
    uglify: {
      options: {
        mangle: true,
        preserveComments: 'some'
      },
      my_target: {
        files: {
          'resources/plugins/admin-lte-2.3.3/js/app.min.js': ['resources/plugins/admin-lte-2.3.3/js/app.js']
        }
      }
    },
    // Build the documentation files
    includes: {
      'resources/build': {
        src: ['*.jsp'], // Source files
        dest: 'documentation/', // Destination directory
        flatten: true,
        cwd: 'documentation/resources/build',
        options: {
          silent: true,
          includePath: 'documentation/resources/build/include'
        }
      }
    },

    // Optimize images
    image: {
      dynamic: {
        files: [{
          expand: true,
          cwd: 'resources/build/img/',
          src: ['**/*.{png,jpg,gif,svg,jpeg}'],
          dest: 'resources/plugins/admin-lte-2.3.3/img/'
        }]
      }
    },

    // Validate JS code
    jshint: {
      options: {
        jshintrc: '.jshintrc'
      },
      core: {
        src: 'resources/plugins/admin-lte-2.3.3/js/app.js'
      },
      demo: {
        src: 'resources/plugins/admin-lte-2.3.3/js/demo.js'
      },
      pages: {
        src: 'resources/plugins/admin-lte-2.3.3/js/pages/*.js'
      }
    },

    // Validate CSS files
    csslint: {
      options: {
        csslintrc: 'resources/build/less/.csslintrc'
      },
      'resources/plugins/admin-lte-2.3.3': [
        'resources/plugins/admin-lte-2.3.3/css/AdminLTE.css',
      ]
    },

    // Validate Bootstrap HTML
    bootlint: {
      options: {
        relaxerror: ['W005']
      },
      files: ['pages/**/*.html', '*.html']
    },

    // Delete images in resources/build directory
    // After compressing the images in the resources/build/img dir, there is no need
    // for them
    clean: {
      'resources/build': ["resources/build/img/*"]
    }
  });

  // Load all grunt tasks

  // LESS Compiler
  grunt.loadNpmTasks('grunt-contrib-less');
  // Watch File Changes
  grunt.loadNpmTasks('grunt-contrib-watch');
  // Compress JS Files
  grunt.loadNpmTasks('grunt-contrib-uglify');
  // Include Files Within HTML
  grunt.loadNpmTasks('grunt-includes');
  // Optimize images
  grunt.loadNpmTasks('grunt-image');
  // Validate JS code
  grunt.loadNpmTasks('grunt-contrib-jshint');
  // Delete not needed files
  grunt.loadNpmTasks('grunt-contrib-clean');
  // Lint CSS
  grunt.loadNpmTasks('grunt-contrib-csslint');
  // Lint Bootstrap
  grunt.loadNpmTasks('grunt-bootlint');

  // Linting task
  grunt.registerTask('lint', ['jshint', 'csslint', 'bootlint']);

  // The default task (running "grunt" in console) is "watch"
  grunt.registerTask('default', ['watch']);
};
